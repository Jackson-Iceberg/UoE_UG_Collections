package linkedlist;

public class SingleLinkedList {
	public static void main(String[] args) {
		// 测试
		// 先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

		// 创建链表
		SingleLinkedListMock singleLinkedList = new SingleLinkedListMock();
//		//加入（方法一）
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero4);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3); 
//		//显示
//		singleLinkedList.list();

		// 加入（方法二）
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
//		singleLinkedList.addByOrder(hero3); check 已经存在method
		// 显示
		singleLinkedList.list();

		// 测试修改节点的代码
		System.out.println("修改后的链表");
		HeroNode newHeroNode = new HeroNode(2, "小卢", "小玉");
		singleLinkedList.update(newHeroNode);
		singleLinkedList.list();

//		System.out.println("删除后的链表");
//		singleLinkedList.del(1);
//		singleLinkedList.del(4);
//		singleLinkedList.del(2);
//		singleLinkedList.del(3);
//		singleLinkedList.list();

		// 测试单链表反转情况
		System.out.println("原列表情况");
		singleLinkedList.list();

		System.out.println("反转后，单列表情况");
		reverseList(singleLinkedList.getHead());
		singleLinkedList.list();
	}
	// 单链表反转
	public static void reverseList(HeroNode head) {
		if(head.next == null || head.next.next == null) {
			return;
		}
		HeroNode curr = head.next;
		HeroNode next = null;
		HeroNode listHead = new HeroNode(0,"","");
		while(curr != null) {
			next = curr.next; //保存curr next 作为temp
			curr.next = listHead.next; // head的前置node，为curr的next。链接作用
			listHead.next = curr; // head的next为curr
			curr = next; // 向后遍历
		}
		head.next = listHead.next;
	}
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedListMock {
	// 初始化一个头节点，头节点不要动，不存放任何数据
	private HeroNode head = new HeroNode(0, "", "");

	public HeroNode getHead() {
		return head;
	}

	public void setHead(HeroNode head) {
		this.head = head;
	}
	

	
	

	// 添加节点到单向链表
	// 思路，当不考虑编号顺序的时候 方法一
	// 1. 找到当今链表的最后节点
	// 2. 将最后这个节点的next指向新的节点
	public void add(HeroNode heroNode) {
		// 因为head节点不能动，因此需要一个辅助遍历temp
		HeroNode temp = head;
		// 遍历链表，找到最后
		while (true) {
			// 找到链表的最后，即null
			if (temp.next == null) {
				break;
			}
			// 否则，继续将temp往后移
			temp = temp.next;
		}
		// 当退出while循环时，temp就指向了链表的最后
		// 将最后这个节点的next指向新的节点
		temp.next = heroNode;
	}

	// 显示链表【遍历】
	public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为头节点不能动，因此我们需要一个辅助变量来遍历
		HeroNode temp = head.next;
		while (true) {
			// 判断是否到链表最后
			if (temp == null) {
				break;
			}
			// 输出节点信息
			System.out.println(temp);
			// 将temp后移
			temp = temp.next;

		}
	}

	// 方法二：添加英雄，根据排名将英雄插入指定位置
	// （如果已经存在该排名，则添加失败，并给出提示）
	public void addByOrder(HeroNode heroNode) {
		// 因为头节点不能动，因此仍然需要通过一个辅助指针（变量）来帮助找到添加的位置
		// 因为单链表，所以我们要找的temp是位于 添加位置的 前一个节点，否则插入不了
		// Node1，Node2，如果要加在node2前面，则需要找到Node1的位置，加在node1后面即可
		HeroNode temp = head;
		boolean flag = false; // flag标记添加的编号是否存在，默认为false
		while (true) {
			if (temp.next == null) {// 说明temp已经在链表的最后
				break;
			}
			if (temp.next.no > heroNode.no) {// 位置找到，在temp的后面插入
				break;
			} else if (temp.next.no == heroNode.no) {// 说明希望添加的heroNode编号已经存在
				flag = true;
				break;
			}
			temp = temp.next; // 往后移，遍历当今链表
		}
		// 判断flag的值，如果为true则说明该英雄已经存在
		if (flag) {
			System.out.printf("准备插入的英雄编号%d已经存在，不能添加\n", heroNode.no);
		} else {
			// 插入到链表中，temp后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}



	// 修改节点的信息，根据no编号来修改，即no编号不能改动
	// 说明：1. 根据newHeroNode 的no来修改即可
	public void update(HeroNode newHeroNode) {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 找到需要修改的节点，根据no编号
		// 定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false;// 是否找到该节点
		while (true) {
			if (temp == null) {
				break; // arriving the end of the linkedlist END
			}
			if (temp.no == newHeroNode.no) {
				// Found it
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// Due to flag, determine whether find out the node
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else {
			System.out.printf("没有找到编号为%d的节点，不能修改\n", newHeroNode.no);
		}
	}

	// 删除节点
	// 思路
	// 1. head不能动，因此需要一个temp辅助节点，并找到待删除节点的前一个节点
	// 2. 在比较时，时temp.next.no 和需要删除的节点的no比较
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false;// 标记是否找到待删除的节点
		while (true) {
			if (temp.next == null) {// 已经到链表的最后
				break;
			}
			if (temp.next.no == no) {
				// 找到 待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next;// temp后移，遍历
		}
		// 判断flag
		if (flag) {// 成功找到
			// 跳过中间节点 = 删除该节点
			temp.next = temp.next.next;
		} else {
			System.out.printf("要删除的%d 节点不存在\n", temp.no);
		}
	}

}

class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;

	// Constructor
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	// 为了显示方法，重新定义toString
	@Override
	public String toString() {
		return " HeroNode [no=" + no + ",name =" + name + ", nickname =" + nickname + "]";
	}

}