package linkedlist;

public class DoubleLinkedListDemo {
	public static void main(String[] args) {
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
		HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
		// 创建链表
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		
		doubleLinkedList.add(hero1);
		doubleLinkedList.add(hero2);
		doubleLinkedList.add(hero3);
		doubleLinkedList.add(hero4);
//		doubleLinkedList.del(4);
//		doubleLinkedList.del(1);
//		doubleLinkedList.del(2);
//		doubleLinkedList.del(3);
//		doubleLinkedList.list();
		HeroNode2 newHero = new HeroNode2(4,"公孙龙","入云龙");
		doubleLinkedList.update(newHero);
		
		doubleLinkedList.list();
	}
}
class DoubleLinkedList{
	// 初始化一个头节点，头节点不要动，不存放任何数据
	private HeroNode2 head = new HeroNode2(0, "", "");
	public HeroNode2 getHead() {
		return head;
	}
	public void setHead(HeroNode2 head) {
		this.head = head;
	}
	public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为头节点不能动，因此我们需要一个辅助变量来遍历
		HeroNode2 temp = head.next;
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
	
	// 添加节点到双向链表
	// 1. 找到当今链表的最后节点
	// 2. 将最后这个节点的next指向新的节点
	// 3. 将最后这个节点的pre指向temp
	// 形成一个双向链表
	public void add(HeroNode2 heroNode) {
		// 因为head节点不能动，因此需要一个辅助遍历temp
		HeroNode2 temp = head;
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
		// 将最后这个节点的pre指向temp
		// 形成一个双向链表
		temp.next = heroNode;
		heroNode.pre = temp;
	}
	
	// 从双向链表中删除节点
	// 1. 对于双向链表，我们可以直接找到要删除的这个节点
	// 2. 找到后，自我删除即可
	public void del(int no) {
		
		//判断当今链表是否为空
		if(head.next == null) {
			System.out.print("该链表为空");
			return;
		}
		
		HeroNode2 temp = head.next; // 辅助变量（指针）
		boolean flag = false;// 标记是否找到待删除的节点
		while (true) {
			if (temp == null) {// 已经到链表的最后
				break;
			}
			if (temp.no == no) {
				// 成功找到
				flag = true;
				break;
			}
			temp = temp.next;// temp后移，遍历
		}
		// 判断flag
		if (flag) {// 成功找到
			// 删除该节点
//			temp.next = temp.next.next;
			temp.pre.next = temp.next;
			// 如果是最后一个节点，就不需要执行下面这一句，否则会产生空指针
			if(temp.next !=null) {				
				temp.next.pre = temp.pre;
			}
		} else {
			System.out.printf("要删除的%d 节点不存在\n", temp.no);
		}
	}
	
	// 修改一个节点的内容，双向链表的节点内容修改和单向链表几乎一样，只是改变了节点类型
	public void update(HeroNode2 newHeroNode) {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 找到需要修改的节点，根据no编号
		// 定义一个辅助变量
		HeroNode2 temp = head.next;
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

}


class HeroNode2{
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;
	public HeroNode2 pre;
	// Constructor
	public HeroNode2(int no, String name, String nickname) {
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
