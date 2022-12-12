package sparsearray;

public class SparseArray {
	
	
	public static void  main(String[] args) {
		// Create a original 2D array 11*11
		// 0 means no chess,1 means black chess, 2 means white chess
		int chessArray[][] = new int [11][11];
		chessArray[1][2] = 1;
		chessArray[2][3] = 2;
		chessArray[2][4] = 2;
		
		for(int [] row: chessArray) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		// 将2D数组转换成稀疏数组
		// 1.遍历2D数组得到非0数据的个数
		// 2 根据sum创建稀疏数组【sum+1】【3】 稀疏数组格式：Row Col Value 第一列为 原数组的Row和Col+sum值
		// 3. 原数组填入稀疏数组
		
		
		// 1.遍历2D数组得到非0数据的个数
		int sum = 0;
		for(int [] row: chessArray) {
			for(int data:row) {
				if(data != 0) {
					sum++;
				}
			}
		}
		
		// 2 根据sum创建稀疏数组【sum+1】【3】 稀疏数组格式：Row Col Value 第一列为 原数组的Row和Col+sum值
		int[][] spareseArray = new int [sum+1][3];
		spareseArray[0][0] = 11;
		spareseArray[0][1] = 11;
		spareseArray[0][2] = sum;
		
		int index = 1;
		// 3. 原数组填入稀疏数组
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(chessArray[i][j] != 0) {
					spareseArray[index][0] = i;
					spareseArray[index][1] = j;
					spareseArray[index][2] = chessArray[i][j];
					index++;
				}
				// cut the useless branches
				if(index == sum+1) {
					break;
					
				}
			}
		}
		for(int [] row: spareseArray) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}		
		
		// 将稀疏数组转换成2D数组
		int[][] chessArray2 = new int[spareseArray[0][0]][spareseArray[0][1]];
		for(int i = 1;i<spareseArray.length;i++) {
			for(int j = 0;j<3;j++) {
				chessArray2[spareseArray[i][0]][spareseArray[i][1]] = spareseArray[i][2];
			}
		}
		for(int [] row: chessArray2) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
	}
}
