package sort;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int array [] = {3,9,-1,10,-2};
		System.out.println("排序前array");
		System.out.println(Arrays.toString(array));
		System.out.println("排序后array");
		bubbleSort(array);
		System.out.println(Arrays.toString(array));
		
	}
	public static void bubbleSort(int [] array) {
		int size = array.length;
		//  冒泡排序的时间复杂度为O(n^2) 两个for loop
		int j=1;
		while(j!=size) {
			// 优化 通过flag来进行剪枝
			boolean flag = false;
			
			// 排序
			for(int i = 0;i<size-j;i++) {
				if(array[i] > array[i+1]) {
					flag =true;
					int temp = array[i+1];
					array[i+1] = array[i];
					array[i] = temp;
				}
			}
			if(flag == false) { // 说明一次交换都没有发生，已经是顺序了，可以剪枝剩下的for loop
				break;
			}
			j++;
		}
	}
	

}
