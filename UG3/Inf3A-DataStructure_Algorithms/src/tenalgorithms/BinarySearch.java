package tenalgorithms;

public class BinarySearch {
	public static void main(String[] args) {
		int[] array = {1,3,8,10,11,67,100};
		int index = binarySearch(array,8);
		System.out.println(index);
		
	}
	
	public static int binarySearch(int[] array,int targetNum) {
		int left = 0;
		int right = array.length-1;
		int mid = (left+right)/2;
		while(left<=right) {
			mid = (left+right)/2;
			if(array[mid] == targetNum) return mid;
			else if(array[mid] > targetNum) right = mid-1;
			else left = mid+1;
		}
		return -1;
	}
}
