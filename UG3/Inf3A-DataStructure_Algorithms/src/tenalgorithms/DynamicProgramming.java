package tenalgorithms;

public class DynamicProgramming {
	public static void main(String[] args) {
		int[] w = {1,4,3}; // 物品重量
		int[] val = {1500,3000,2000}; //物品价值
		int m = 4;//背包容量
		int n = val.length; // 物品的个数
		DP(w,val,m,n);
	}
	// 解决背包问题
	public static void DP(int[] w,int [] val,int m,int n) {
		//创建二维数组，array[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] array = new int [n+1][m+1];
		// 初始化第一行和第一列
		for(int i = 0;i<array.length;i++) {
			array[i][0] = 0; //将第一列设为0
		}
		for(int i = 0;i<array[0].length;i++) {
			array[0][i] = 0; // 将第一行设为0
		}
		
		for(int i = 1;i<array.length;i++) {
			for(int j = 1;j<array[0].length;j++) {
				if(w[i-1]>j) {// 因为我们程序i是从1开始的，因此原来公式中的w[i] 修改成w[i-1]
					array[i][j] = array[i-1][j];
				}else {
					// 因为i从1开始的 调整公式
//					if(array[i-1][j] < val[i-1] + array[i-1][j-w[i-1]]) {
					array[i][j] = Math.max(array[i-1][j],val[i-1] + array[i-1][j-w[i-1]]);
//					}
				}
			}
		}
		for(int i = 0;i<array.length;i++) {
			for(int j = 0;j<array[0].length;j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
}
