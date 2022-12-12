package sort;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		int [] array = {8,4,5,7,1,3,6,2};
		int [] temp = new int[array.length];
		mergeSort(array,0,array.length-1,temp);
		System.out.println("归并排序后=" + Arrays.toString(array));
	}
	
	// 分+合方法
	public static void mergeSort(int [] array,int left,int right,int[] temp) {
		if(left<right) {
			int mid = (left+right)/2;
			// 向左递归进行分解
			mergeSort(array,left,mid,temp);
			// 向右递归进行分解
			mergeSort(array,mid+1,right,temp);
			// 合并
			merge(array,left,mid,right,temp);
		}
	}
	
	// array 排列的原始数组
	// left 左边有序序列的初始索引
	// mid 中间索引
	// right 右边索引
	// temp做中转数组
	public static void merge(int[] array,int left,int mid,int right,int[] temp) {
		int i = left; // 初始化i，左边有序序列的初始索引
		int j = mid+1; // 初始话j，右边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引
	
		// step1
		// 先把左右两边（有序）的数据按照规矩填充到temp数组
		// 直到左右两边的有序序列，有一边处理完毕
		while(i<=mid && j<=right) {
			// 如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
			// 将左边的当前元素，填充到temp数组
			// 然后t++，i++
			if(array[i]<=array[j]) {
				temp[t] = array[i];
				t++;
				i++;
			}else {//反之，将右边有序序列的当前元素，填充到temp数组
				   //t++，i++
				temp[t] = array[j];
				t++;
				j++;
			}
		}
		
		
		// step2
		// 把有剩余数据的一边的数组数据，依次全部填充到temp
		while(i<=mid) { // 左边的有序序列还有剩余的元素，全部填充到temp
			temp[t] = array[i];
			t++;
			i++;
		}
		while(j<=right) { // 右边的有序序列还有剩余的元素，全部填充到temp
			temp[t] = array[j];
			t++;
			j++;
		}
		
		
		// step3
		// 将temp数组的元素拷贝到array
		// 注意不是每次都拷贝所有
		t = 0;
		int tempLeft = left;
		// 第一次合并tempLeft = 0,right = 1 // tempLeft = 2,right = 3// tL = 0,right =3//
		// 最后一次合并tempLeft = 0,right = 7
		while(tempLeft <= right) {
			array[tempLeft] = temp[t];
			t++;
			tempLeft++;
		}
	
	}
}
