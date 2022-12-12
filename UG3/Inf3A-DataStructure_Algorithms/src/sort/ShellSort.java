package sort;

import java.util.Arrays;

public class ShellSort {
	public static void main(String[] args) {
		int [] array1 = {8,9,1,7,2,3,5,4,6,0};
		int [] array2 = {8,9,1,7,2,3,5,4,6,0};
		System.out.println("希尔排序前的原始数组");
		System.out.println(Arrays.toString(array1));
//		System.out.println("交换法的希尔排序");
//		shellSort(array1);
		System.out.println("移动法的希尔排序");
		shellSort2(array2);
	}
	
	//使用逐步推导的方式来编写希尔排序
	public static void shellSort(int[]array) {
		
		int temp = 0;
		int gap = array.length/2;
		int count = 1;
		while(gap>0) {
			
		for(int i = gap;i<array.length;i++) {
			// 遍历各组中所有的元素（共5组，每组2个元素），步长为5
			for(int j = i-gap;j>=0;j-=gap) {
				//  如果当今元素大于 加上步长后的元素，说明交换
				if(array[j]>array[j+gap]) {
					temp = array[j];
					array[j] = array[j+gap];
					array[j+gap] = temp;
				}
			}
		}
		System.out.println("希尔排序"+count+"轮后的原始数组");
		System.out.println(Arrays.toString(array));
		gap = gap/2;
		count++;
			
		}
	}
		
		
		
//		// 希尔排序第一轮
//		// 因为是第一轮排序，所以将10个数据分成了5组
//		// 希尔排序第二轮
//		// 因为是第二轮排序，所以将10个数据分成了5/2=2组
//		for(int i = 5;i<array.length;i++) {
//			// 遍历各组中所有的元素（共2组，每组2个元素），步长为5
//			for(int j = i-5;j>=0;j-=5) {
//				//  如果当今元素大于 加上步长后的元素，说明交换
//				if(array[j]>array[j+5]) {
//					temp = array[j];
//					array[j] = array[j+5];
//					array[j+5] = temp;
//				}
//			}
//		}
		
	// 移位法 希尔排序
	public static void shellSort2(int[]array) {
		// 增量gap，并逐步的缩小增量
		int count = 1;
		int gap = array.length/2;
		while(gap>0) {
			for(int i = gap;i<array.length;i++) {
				int j = i;
				int temp = array[j];
				if(array[j] < array[j-gap]) {
					while(j - gap >=0 && temp < array[j-gap]) {
						// 移动
						array[j] = array[j-gap];
						j = j-gap;
					}
					// 当退出while后，就给temp找到插入的位置
					array[j] = temp;
				}
			}
			System.out.println("希尔排序"+count+"轮后的原始数组");
			System.out.println(Arrays.toString(array));
			count++;
			gap = gap/2;
		}
		
	}
}
