package queue;
import java.util.*;


// 问题分析&优化 
// 1. 目前数组只能使用一次，没有实现复用功能
// 2. 使用算法改进，改进成环形的队列，实现复用；tips：取模： %

public class ArrayQueue {
	public static void main(String[]args) {
		// Test
		// Create a new ArrayQueue;
		ArrayQueueMock arrayQueue = new ArrayQueueMock(3);
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
//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueueMock{
	private int maxSize;// 表示数组的最大容量
	private int front; // 队列头
	private int end; // 队列尾
	private int [] array; // 该数据用于存放数据，模拟队列
	
	//创建队列的构造器
	public ArrayQueueMock(int arrMaxSize) {
		maxSize = arrMaxSize;
		array = new int [maxSize];
		front = -1; //指向队列头部，分析出front是指向队列头的前一个位置
		end = -1; // 指向队列尾，指向队列尾的数据（即队列最后一个数据）
	}
	// 判断队列是否满
	public boolean isFull() {
		return end == maxSize -1;
	}
	// 判断队列是否为空
	public boolean isEmpty() {
		return front == end;
	}
	// 添加数据到队列
	public void addQueue(int num) {
		if(isFull()) {
			System.out.println("队列已满，不能添加数据");
			return;
		}
		end++; //end后移
		array[end] = num; // 把数据添加进去
	}
	// 出数据，获取队列的数据，出队列
	public int getQueue() {
		//判断队列是否为空
		if(isEmpty()) {
			// 抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		front++;
		return array[front];
	}
	//显示队列的所有数据
	public void showQueue() {
		// 遍历
		if(isEmpty()) {
			throw new RuntimeException("队列空的，没有数据");
		}
		for(int i = 0;i<array.length;i++) {
			System.out.printf("array[%d]=%d\n", i,array[i]);
		}
	}
	// 显示队列的头数据，不是取出数据
	public int showHeadQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列是空的，没有数据");
		}
		return array[front+1];
	}
}
