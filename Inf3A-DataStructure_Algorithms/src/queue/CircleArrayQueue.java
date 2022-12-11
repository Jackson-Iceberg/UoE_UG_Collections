package queue;
import java.util.Scanner;

public class CircleArrayQueue {
	public static void main(String[] args) {
		System.out.println("测试");
		CircleArray arrayQueue = new CircleArray(4); //设置4，其队列有效数据最大为3 （约定）
		char key = ' '; //接受用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		// 输出一个菜单
		while(loop) {
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取出数据");
			System.out.println("h(head):查看队列的头数据");
			key = scanner.next().charAt(0); // get the input char
			switch (key) {
			case 's':
				arrayQueue.showQueue();
				break;
			case 'a':
				System.out.println("输入一个数，添加进数组");
				int value = scanner.nextInt();
				arrayQueue.addQueue(value);
				break;
			case 'e':
				scanner.close();
				System.out.println("程序退出");
				loop = false;
				break;
			case 'g': //取出数据，因为可能存在空数组，需要用try catch
				try {
					int res = arrayQueue.getQueue();
					System.out.printf("取出的数据是%d\n",res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h': //可能存在空数组，需要用try catch
				try {
					int res = arrayQueue.showHeadQueue();
					System.out.printf("头数据是%d\n",res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
			

		}
	}
}
// 数组循环-环形队列
// front变量的初始值为0，front就指向队列的第一个元素
// end初始值为0，end指向队列的最后一个元素+1，空出来一个空间作为约定
// 队列满的情况为：（end+1） % maxSize = front
// 队列空的情况为：end == front
// 队列中有限的数据的个数：（end+maxSize-front）% maxSize
class CircleArray{
	private int maxSize;// 表示数组的最大容量
	private int front; // 队列头
	private int end; // 队列尾
	private int [] array; // 该数据用于存放数据，模拟队列
	
	//创建队列的构造器
	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		array = new int [maxSize];
		front = 0; // front变量的初始值为0，front就指向队列的第一个元素
		end = 0; // end初始值为0，end指向队列的最后一个元素+1，空出来一个空间作为约定
	}
	// 判断队列是否满
	public boolean isFull() {
		// 队列满的情况为：（end+1） % maxSize = front
		return (end+1)%maxSize == front;
	}
	// 判断队列是否为空
	public boolean isEmpty() {
		// 队列空的情况为：end == front
		return end == front;
	}
	// 添加数据到队列
	public void addQueue(int num) {
		if(isFull()) {
			System.out.println("队列已满，不能添加数据");
			return;
		}
		array[end] = num; // 把数据添加进去
		end = (end+1) % maxSize;
	}
	// 出数据，获取队列的数据，出队列
	public int getQueue() {
		//判断队列是否为空
		if(isEmpty()) {
			// 抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		// front是指向队列的第一个元素
		// 1. 先把front对应的值保留到一个临时变量
		// 2. 将front后移，取模
		// 3. 将临时保存的变量返回
		int val = array[front];
		front = (front+1) % maxSize;
		return val;
	}
	//显示队列的所有数据
	public void showQueue() {
		// 遍历
		if(isEmpty()) {
			throw new RuntimeException("队列空的，没有数据");
		}
		// 从front开始遍历，遍历完
		
		for(int i = front;i<front+ValidSize();i++) {
			System.out.printf("array[%d]=%d\n", i%maxSize,array[i%maxSize]);
		}
	}
	public int ValidSize() {
		return (end+maxSize-front)%maxSize;
	}
	// 显示队列的头数据，不是取出数据
	public int showHeadQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列是空的，没有数据");
		}
		return array[front];
	}	
}