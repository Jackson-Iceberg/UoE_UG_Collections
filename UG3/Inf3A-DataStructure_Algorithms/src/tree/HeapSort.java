package tree;

import java.util.Arrays;

public class HeapSort {
	public static void main(String[] args) {
		
		int [] array = {4,6,8,5,9};
		heapSort(array);
	}
	
	// 编写一个堆排序的方法
	public static void heapSort(int[] array) {
		int temp = 0;
		System.out.println("堆排序");
		
//		adjustHeap(array,1,array.length);
//		System.out.println("第一次"+Arrays.toString(array)); // 4,9,8,5,6
//		
//		adjustHeap(array,0,array.length);
//		System.out.println("第二次"+Arrays.toString(array)); // 9,6,8,5,4
		
		for(int i = array.length/2-1;i>=0;i--) {
			adjustHeap(array,i,array.length);
		}
		
		/*
		 * 2. 将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
		 * 3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
		 */
		for(int j = array.length-1;j>0;j--) {
			// 交换
			temp = array[j];
			array[j] = array[0];
			array[0] = temp;
			adjustHeap(array,0,j);
		}
		
		System.out.println("数组="+Arrays.toString(array));
		
	}
	
	// 将一个数组（二叉树），调整成一个大顶堆
	/**
	 * 功能：完成将以i对应的非叶子结点的树调整成大顶堆
	 * 举例：int[] array = {4,6,8,5,9} => 1= 1 => adjustHeap => 得到{4,9,8,5,6}
	 * 如果我们再次调用adjustHeap 传入的是 i = 0 => 得到{4,9,8,5,6} => {9,6,8,5,4}
	 * @param array 待调整的数组
	 * @param i 表示非叶子结点在数组中索引
	 * @param length 表示对多少个元素继续调整，length是逐渐减少的
	 */
	public static void adjustHeap(int [] array,int i,int length) {
		int temp = array[i];//先取出当前元素的值，保存在临时变量
		// 开始调整
		// 1. k=i*2+1, k是i结点的左子结点
		for(int k = i*2+1;k<length;k = k*2+1) {
			if(k+1<length && array[k] < array[k+1]) { // 说明左子节点的值小于右子节点的值
				k++; // k指向右子结点
			}
			if(array[k]>temp) { // 如果子节点大于父结点
				array[i] = array[k]; // 把较大的值赋给当前结点
				i = k;  // i指向k，继续循环比较
			}
			else {
				break;
			}
		}
		// 当for循环结束后，以i为父节点的树的最大值，放在了最顶（局部）
		array[i] = temp; // 将temp值放在调整后的位置
	}
}
