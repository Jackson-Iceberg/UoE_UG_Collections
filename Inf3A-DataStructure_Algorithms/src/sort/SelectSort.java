package sort;
public class SelectSort {
	public static void main(String[] args) {
		int [] array = {1,34,119,101};
//		System.out.println(Arrays.toString(array));
		selectSort(array);
//		System.out.println(Arrays.toString(array));
		
	}
	
	//选择排序
	public static void selectSort(int[] array) {
		
		
		// 算法解决idea：先简单-》做复杂，把复杂的算法，拆分成简单的问题，逐步解决
		// 使用逐步推导的方式来讲解：选择排序
		// 原始数组 101，34，119，1
		// 第一轮
		for(int i = 0;i<array.length-1;i++) {
			int minIndex = i;
			int min = array[i];
			for(int j = i+1;j<array.length;j++) {
				if(min>array[j]) {
					min = array[j];
					minIndex = j;
				}
			}
			array[minIndex] = array[i];
			array[i] = min;
			
//			System.out.println(Arrays.toString(array));
		}
		
	}
}
