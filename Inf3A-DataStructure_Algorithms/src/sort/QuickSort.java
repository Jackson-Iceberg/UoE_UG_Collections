package sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int [] array = {-9,78,23,0,-567,70};
		System.out.println(Arrays.toString(array));
		quickSort(array,0,array.length-1);
		System.out.println(Arrays.toString(array));
	}
	
	public static void quickSort(int[] array,int left,int right) {
		int l = left; //左下标
		int r = right; // 右下标
		// pivot 中轴
		int pivot = array[(left+right)/2];
		int temp= 0;
		// while循环的目的是让比pivot值小的放在左边
		// 比 pivot 值大放到右边
		while(l < r) {
			// 在pivot的左边一直找，找到大于等于pivot值，才退出
			while(array[l] < pivot) {
				l++;
			}
			// 在pivot的右边一直找，找到小于等于pivot值，才退出
			while(array[r]>pivot) {
				r--;
			}
			// l>r 说明比较已经结束
			if(l >= r) {
				break;
			}
			
//			System.out.printf("pivot值=%d,left值=%d,right值=%d",pivot,l,r);
//			System.out.println();
			// 交换
			temp = array[r];
			array[r] = array[l];
			array[l] = temp;
//			System.out.println(Arrays.toString(array));
			
			// 如果交换后，发现这个array[l] == pivot值，r-- 前移
			// 说明l的已经遍历完了，说明左边已经检查完了，只能改变r值，来检查右边
			if(array[l] == pivot) {
				r--;
			}
			// 如果交换后，发现这个array[r] == pivot值，l++ 后移
			// 说明r的已经遍历完了，说明右边已经检查完了，只能改变l值，检查左边
			if(array[r] == pivot) {
				l++;
			}
		}
		// 如果left = right，必须l++，r--，否则会出现栈溢出
		if(l == r) {
			l++;
			r--;
		}
		// 向左递归
		if(left<r) {
			quickSort(array,left,r);
		}
		// 向右递归
		if(right>l) {
			quickSort(array,l,right);
		}

	}
}
