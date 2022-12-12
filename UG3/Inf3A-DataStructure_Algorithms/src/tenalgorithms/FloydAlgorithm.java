package tenalgorithms;

import java.util.Arrays;

public class FloydAlgorithm {
	public static void main(String[] args) {
		char[] vertex = {'A','B','C','D','E','F','G'};
		final int N = 65535; // 表示不可连接
		int[][] matrix = {
				{0,5,7,N,N,N,2},
				{5,0,N,9,N,N,3},
				{7,N,0,N,8,N,N},
				{N,9,N,0,N,4,N},
				{N,N,8,N,0,5,4},
				{N,N,N,4,5,0,6},
				{2,3,N,N,4,6,0},
				};
		FloydGraph graph = new FloydGraph(vertex.length,matrix,vertex);
		graph.floyd();
		graph.show();
	}
}

class FloydGraph{
	private char [] vertex;
	private int[][] dis;
	private int[][] pre;
	public FloydGraph(int length,int[][] matrix,char[]vertex) {
		this.vertex = vertex;
		this.dis = matrix;
		this.pre = new int [length][length];
		for(int i = 0;i<length;i++) {
			Arrays.fill(pre[i], i);
		}
	}
	
	public void show() {
		char[] vertex = {'A','B','C','D','E','F','G'};
		for(int k = 0;k<dis.length;k++) {
			for(int i = 0;i<dis.length;i++) {
				System.out.print(vertex[pre[k][i]]+" ");
			}
			System.out.println();
			
			for(int i = 0;i<dis.length;i++) {
				System.out.print("("+vertex[k]+"到"+vertex[i]+"的最短路径是"+dis[k][i]+")");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public void floyd() {
		int len = 0;
		for(int k = 0;k<dis.length;k++) {
			for(int i = 0;i<dis.length;i++) {
				for(int j = 0;j<dis.length;j++) {
					len = dis[i][k] + dis[k][j];
					if(len<dis[i][j]) {
						dis[i][j] = len;
						pre[i][j] = pre[k][j];
					}
				}
			}
		}
	}
	
}
