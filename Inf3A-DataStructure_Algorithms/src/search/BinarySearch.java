package search;

import java.util.ArrayList;

// 使用二分查找的前提是 该数组是有序的
public class BinarySearch {
	public static void main(String[] args) {
		int [] array = {1,8,10,89,1000,1234};
		int index = binarySearch(array,0,array.length-1,88);
		if(index == -1) System.out.println("Index不存在");
		else System.out.println("Index="+index);
		
		int [] array2= {1,88,10,89,1000,1000,1000,1000,1234};
		ArrayList<Integer> result = binarySearch2(array2,0,array2.length-1,1000);
		System.out.println("result="+result);
		
	}
	// 二分查找算法
	public static int binarySearch(int[] array,int left,int right,int finalVal) {
		if(left>right) return -1;
		
		int mid = (left+right)/2;
		int midVal = array[mid];
		
		if(finalVal>midVal) { // 向右递归
			return binarySearch(array,mid+1,right,finalVal);
		}
		else if(finalVal<midVal) {
			return binarySearch(array,left,mid-1,finalVal);
		}
		else {
			return mid;
		}
	}
	// 二分查找算法
	public static ArrayList<Integer> binarySearch2(int[] array,int left,int right,int finalVal) {
		if(left>right) return new ArrayList<Integer>();
		
		int mid = (left+right)/2;
		int midVal = array[mid];
		
		if(finalVal>midVal) { // 向右递归
			return binarySearch2(array,mid+1,right,finalVal);
		}
		else if(finalVal<midVal) {
			return binarySearch2(array,left,mid-1,finalVal);
		}
		else {
			// 1. 在找到mid之后不立刻返回
			// 2. 向mid索引的左边扫描，将所有满足的元素下标加入到arraylist集合中
			// 3. 向mid索引的右边扫描，将所有满足的元素下标加入到arraylist集合中
			ArrayList<Integer> result = new ArrayList<>();
			
			
			// 测试mid左边 
			int temp = mid-1;
			while(true) {
				if(array[temp] != finalVal || temp<0) {
					break;
				}
				result.add(temp);
				temp -= 1;
			}
			result.add(mid);
			// 测试mid右边
			temp = mid+1;
			while(true) {
				if(array[temp] != finalVal || temp<0) {
					break;
				}
				result.add(temp);
				temp += 1;
			}
			
			return result;
		}
	}
}
