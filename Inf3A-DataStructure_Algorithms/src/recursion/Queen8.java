package recursion;

public class Queen8 {
	
	// 定义一个max表示共有多少个皇后
	int max = 8;
	// 定义数组array，保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}
	int [] array = new int [max];
	static int count = 0;
	public static void main(String[] args) {
		Queen8 queen8 = new Queen8();
		queen8.check(0);
		System.out.printf("一共有%d种解法",count);
	}
	
	// 编写一个方法，放置第n个皇后
	// 特别注意，check是每一次递归，进入到check都有一个for loop；因此会有回溯
	private void check(int n) {
		if (n==max) { // n = 8, 其实8个皇后已经放好了
			print();
			return;
		}
		for(int i = 0;i<max;i++) {
			// 先把当前这个皇后n，放到该行的第一列
			array[n] = i;
			//检查是否冲突，检查当今放置第n个皇后到i列是否冲突
			if(judge(n)) {//不冲突
				check(n+1);
			}
			//如果冲突，就继续执行array【n】 = i；即把第n个皇后，放置在本行的后移一个位置，遍历整个行
		}
	}
	
	
	// 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
	// n 表示第n个皇后 
	private boolean judge(int n) {
		for(int i = 0;i<n;i++) {
			// 说明&思路
			// 1. array[i] == array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
			// 2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断 第n个皇后是否和第i个皇后是否在同一斜线
			// n = 1 放置 第二列第一行 n=1 array【1】 = 1
			// Math.abs(1-0) == 1; Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
			// 判断是否在同一行，没有必要，因为n每次都在递增
			if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// 写一个方法，可以将皇后的摆放位置输出
	private void print() {
		count++;
		for(int i = 0;i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	
}
