package binarysorttree;

public class BinarySortTreeDemo {
	public static void main(String[] args) {
		int[] array = {7,3,10,12,5,1,9};
		BinarySortTree bst = new BinarySortTree();
		// 循环的添加结点到二叉排序树
		for(int i = 0;i<array.length;i++) {
			bst.add(new Node(array[i]));
		}
		// 中序遍历二叉排序树
		System.out.println("中序遍历二叉排序树");
		bst.infixOrder();
		
		// 测试一下删除叶子结点
		bst.deleNode(2);
		bst.deleNode(5);
		bst.deleNode(9);
		bst.deleNode(12);
		bst.deleNode(7);
		bst.deleNode(3);
		bst.deleNode(10);
		bst.deleNode(1);

		System.out.println("删除结点后的二叉排序树");
		bst.infixOrder();
	}
}

// 创建二叉排序树
class BinarySortTree{
	private Node root;
	// 查找要删除的结点
	public Node search(int value) {
		if(root == null) {
			return null;
		}else return root.search(value);
	}
	
	// 查找父结点
	public Node searchParent(int value) {
		if(root == null) return null;
		else return root.searchParent(value);
	}
	// 编写方法：
	// 1. 返回的以node为根结点的二叉排序树的最小结点的值
	// 2. 删除node为根结点的二叉排序树的最小结点
	// node为传入的结点 当作二叉排序树的根结点
	// 返回以node为跟结点的二叉排序树的最小结点的值
	public int delRightTreeMin(Node node) {
		Node target = node;
		// 循环查找左结点，就会找到最小值
		while(target.left!=null) {
			target = target.left;
		}
		// 删除最小结点
		deleNode(target.value);
		return target.value;
	}
	
	
	
	// 删除结点
	public void deleNode(int value) {
		if(root == null) return;
		else {
			// 1. 先找到要删除的结点
			Node targetNode = search(value);
			// 如果没有找到要删除的结点
			if(targetNode == null) {
				return;
			}
			// 如果发现这个二叉排序树只有一个结点
			if(root.left == null && root.right == null) {
				root = null;
				return;
			}
			// 找targetNode的父节点
			Node parent = searchParent(value);
			// 如果要删除的是叶子结点，去判断该结点是父节点的左子结点还是右子结点
			if(targetNode.left == null && targetNode.right == null) {
				if(parent.left != null && parent.left.value == value) {
					parent.left = null;
				}else if(parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
			}
			// 要删除的是有两个子树的结点
			else if(targetNode.left != null && targetNode.right != null) {
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;
			}
			// 要删除的是有一个子树的结点
			else {
				// 要删除的结点有左子结点
				if(targetNode.left != null) {
					if(parent != null) {						
						if(parent.left.value == value) {
							parent.left = targetNode.left;
						}else { // targetNode是parent的右子结点
							parent.right = targetNode.left;
						}
					}else {
						root = targetNode.left;
					}
				}else { // 要删除的结点有右子结点
					if(parent != null) {						
						if(parent.left.value == value) {
							parent.left = targetNode.right;
						}else {
							parent.right = targetNode.right;
						}
					}else {
						root = targetNode.right;
					}
				}
			}
			
			
		}
	}
	
	// 添加结点
	public void add(Node node) {
		if(root == null) {
			root = node;
		}else {
			root.add(node);
		}
	}
	//中序遍历
	public void infixOrder() {
		if(root != null) {
			root.infixOrder();
		}else {
			System.out.println("二叉排序树为空，不能遍历");
		}
	}
}


class Node{
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value = value;
	}
	// 查找删除的结点
	// value 即希望删除的结点的值
	public Node search(int value) {
		if(value == this.value) {
			return this;
		}
		else if(value < this.value) {
			// 如果左子节点为空
			if(this.left == null) {
				return null;
			}else {
				return this.left.search(value);
			}
		}
		else {
			if(this.right == null) {
				return null;
			}else {
				return this.right.search(value);
			}
		}
	}
	// 查找要删除结点的父节点
	// value是要删除结点的值
	// 返回要删除结点的父节点，如果没有则返回null
	public Node searchParent(int value) {
		// 如果当前结点就是要删除的结点的父节点，就返回
		if((this.left != null && this.left.value == value)||
			(this.right != null && this.right.value == value)) {
			return this;
		}
		// 如果查找的值小于当前结点的值，且当前结点的左子结点不为空，往左边去找
		else {
			if(value < this.value && this.left != null) {
				return this.left.searchParent(value);
			}
			else if(value > this.value && this.right != null) {
				return this.right.searchParent(value);
			}else return null; // 没有找到
		}
	}
	
	/// 添加结点的方法
	// 递归的形式添加结点，注意需要满足二叉排序树的要求
	public void add(Node node) {
		if(node == null) {
			return;
		}
		
		// 判断传入的结点的值，和当前子树的根结点的值关系
		if(node.value < this.value) {//传入结点比当前结点小
			// 检查左结点是否为空
			if(this.left == null) {
				this.left = node;
			}else {
				// 递归的向左子树添加
				this.left.add(node);
			}
		}
		else {//传入结点比当前结点的值大，或者相等
			if(this.right == null) {
				this.right = node;
			}else {
				this.right.add(node);
			}
		}		
	}
	//中序遍历
	public void infixOrder() {
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.infixOrder(); 
		}
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
}