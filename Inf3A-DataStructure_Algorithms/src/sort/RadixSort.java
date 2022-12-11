package sort;

import java.util.Arrays;

public class RadixSort {
	public static void main(String[] args) {
		int [] array = {53,3,542,748,14,214};
		radixSort(array);
	}
	
	// 基数排序
	public static void radixSort(int[] array) {
		// 1. 得到数组中最大数的位数
		int max = array[0];
		for(int i = 1;i<array.length;i++) {
			if(array[i]>max) {
				max = array[i];
			}
		}
		int maxLength = (max+"").length();
		
		
		// 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		// 1. 二维数组包含10个一维数组
		// 2. 为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为array.length
		// 3. ps：基数排序是使用空间换时间的经典算法
		int [] [] bucket = new int [10][array.length];
		// 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录每个桶的每次放入的数据个数
		// 比如：bucketElementCounts[0],记录的就是bucket[0]桶的放入数据个数
		int[] bucketElementCounts = new int [10];
		for(int i = 0,n=1;i<maxLength;i++,n*=10) {
			// 第n轮（针对每个元素的个位进行排序处理）
			for(int j = 0;j < array.length;j++) {
				// 取出每个元素的个位的值
				int digitOfElement = array[j]/n%10;
				// 放入到对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
				bucketElementCounts[digitOfElement]++;
			}
			
			// 按照这个桶的顺序（一维数组的下标依次取出数据，放回原来数组）
			int index = 0;
			// 遍历每一桶，并将桶中数据，放入到原数组
			for(int k = 0;k<bucketElementCounts.length;k++) {
				//如果桶中有数据，才放入原数组
				if(bucketElementCounts[k] != 0) {
					// 循环该桶（即第k个桶-即第k个一维数组），放入
					for(int l = 0;l<bucketElementCounts[k];l++) {
						// 取出元素放入到array
						array[index] = bucket[k][l];
						index++;
					}
				}
				bucketElementCounts[k] = 0;
			}
			System.out.println("第"+(i+1)+"轮，对个位的排序处理 array = "+Arrays.toString(array));
		}
		
		
	}
}
