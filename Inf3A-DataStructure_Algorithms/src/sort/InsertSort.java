package sort;

import java.util.Arrays;

public class InsertSort {
	public static void main(String[] args) {
		int[] array = {101,34,119,1,-1,89};
		insertSort(array);
	}
	
	
	public static void insertSort(int[] array) {
		//使用逐步推导方便理解
		for(int i = 1;i<array.length;i++) {
			
			int insertIndex = i-1;
			int insertValue = array[i];
			// 给insertVal找到插入的位置
			// 1. insertIndex 》= 0 保证 index不越界
			// 2. insertVal 《 array【insertIndex】 说明代插入的数，还没找到插入位置
			// 3. 需要将array【insertIndex】后移
			while(insertIndex >= 0 && insertValue < array[insertIndex]) {
				array[insertIndex+1] = array[insertIndex]; // array【insertIndex】往后移
				insertIndex--;
			}
			// 当退出while循环，说明插入位置已经找到，insertIndex+1 因为前面insertIndex-1了
			array[insertIndex+1] = insertValue;
			System.out.println("第"+i+"次插入");
			System.out.println(Arrays.toString(array));
		}
	}
}
