package tenalgorithms;

import java.util.Arrays;

public class DijkstraAlgorithm {
	public static void main(String[] args) {
		char[] vertex = {'A','B','C','D','E','F','G'};
		final int N = 65535; // 表示不可连接
		int[][] matrix = {
				{N,5,7,N,N,N,2},
				{5,N,N,9,N,N,3},
				{7,N,N,N,8,N,N},
				{N,9,N,N,N,4,N},
				{N,N,8,N,N,5,4},
				{N,N,N,4,5,N,6},
				{2,3,N,N,4,6,N},
				};
		Graph graph = new Graph(vertex,matrix);
		graph.showGraph();
		graph.dijkstral(6);
		graph.showResult();
	}
}
class Graph{
	private char[] vertex;
	private int[][] matrix;
	private VisitedVertex vv;
	public Graph(char[] vertex,int[][] matrix) {
		this.vertex = vertex;
		this.matrix = matrix;
	}
	public void showGraph() {
		for(int[] link:matrix) {
			System.out.println(Arrays.toString(link));
		}
	}
	public void showResult() {
		vv.showResult();
	}
	public void dijkstral(int index) {
		vv = new VisitedVertex(vertex.length,index);
		update(index);
		for(int j = 1;j<vertex.length;j++) {
			index = vv.updateArr();
			update(index);
		}
	}
	
	private void update(int index) {
		int len = 0;
		for(int j = 0;j<matrix[index].length;j++) {
			// len的含义：出发顶点到index顶点的距离+从index结点到j顶点距离的和
			len = vv.getDis(index)+matrix[index][j];
			// 如果j结点没有被访问过，且len小于出发结点到j结点的距离，就需要更新
			if(!vv.in(j)&&len<vv.getDis(j)) {
				vv.updatePre(j, index); // 更新j顶点的前驱为index顶点
				vv.updateDis(j, len); // 更新出发顶点到j顶点的距离
			}
		}
	}
	
}
class VisitedVertex{
	public int [] already_arr;
	public int [] pre_visited;
	public int [] dis;
	
	public VisitedVertex(int length,int index) {
		this.already_arr = new int [length];
		this.pre_visited = new int [length]; 
		this.dis = new int [length];
		Arrays.fill(this.dis, 65535);
		this.already_arr[index] = 1;
		this.dis[index] = 0;
	}
	// 功能判断：index顶点是否被访问过
	// 如果访问过，就返回true，否则false
	public boolean in(int index) {
		return already_arr[index] == 1;
	}
	public void updateDis(int index,int len) {
		dis[index] = len;
	}
	public void updatePre(int pre,int index) {
		pre_visited[pre] = index;
	}
	public int getDis(int index) {
		return dis[index];
	}
	public int updateArr() {
		int min = 65535,index = 0;
		for(int i = 0;i<already_arr.length;i++) {
			if(already_arr[i] == 0 && dis[i] < min) {
				min = dis[i];
				index = i;
			}
		}
		already_arr[index] = 1;
		return index;
	}
	
	public void showResult() {
		System.out.println("---------------");
		for(int i : already_arr) {
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i : pre_visited) {
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i : dis) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}