package search;

// 插值查找算法是二分查找的改进，也需要数组是有序的
public class InsertValueSearch {
	public static void main(String[] args) {
		int[] array = new int[100];
		for(int i = 0;i<100;i++) {
			array[i] = i+1;
		}
		int result = insertValueSearch(array,0,array.length-1,100);
		System.out.println(result);
		
		
	}
	public static int insertValueSearch(int[] array,int left,int right,int findVal) {
		// 注意 findVal<array[0] 和findVal>array[array.length-1]必须需要
		// 否则我们得到的mid可能越界
		if(left>right || findVal<array[0] || findVal > array[array.length-1]) {
			return -1;
		}
		
		int mid = left+(right-left)*(findVal - array[left]) / (array[right]-array[left]);
		if(findVal>array[mid]) { // 说明应该向右边递归
			return insertValueSearch(array,mid+1,right,findVal);
		}else if (findVal<array[mid]) { // 说明应该向左递归
			return insertValueSearch(array,left,mid-1,findVal);
		}else {
			return mid;
		}
		
	}
}
