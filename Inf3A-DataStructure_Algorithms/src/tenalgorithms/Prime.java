package tenalgorithms;

import java.util.Arrays;

public class Prime {
	public static void main(String[] args) {
		char[] data = {'A','B','C','D','E','F','G'};
		int vertes = data.length;
		int [] [] weight = new int [][] {
			// 10000 表示此路不通
			{10000,5,7,10000,10000,10000,2},
			{5,10000,10000,9,10000,10000,3},
			{7,10000,10000,10000,8,10000,10000},
			{10000,9,10000,10000,10000,4,10000},
			{10000,10000,8,10000,10000,5,4},
			{10000,10000,10000,4,5,10000,6},
			{2,3,10000,10000,4,6,10000},};
		
		MGraph mGraph = new MGraph(vertes);
		MinTree minTree = new MinTree();
		minTree.createGraph(mGraph, vertes, data, weight);
		minTree.showGraph(mGraph);
		minTree.Prime(mGraph, 0);
	}
	
}
class MinTree{
	public void createGraph(MGraph mgraph,int vertes,char[] data,int[][] weight) {
		for(int i = 0;i<vertes;i++) {
			mgraph.data[i] = data[i];
			for(int j = 0;j<vertes;j++) {
				mgraph.weight[i][j] = weight[i][j];
			}
		}
	}
	public void showGraph(MGraph graph) {
		for(int[] links:graph.weight) {
			System.out.println(Arrays.toString(links));
		}
	}
	
	public void Prime(MGraph graph,int startIndex) {
		int size = graph.vertes;
		int[] visited = new int [size];
		visited[startIndex] = 1;
		// n 个点， n-1个边
		for(int k = 0;k<size-1;k++) {
			int min = 10000;
			int visitA = -1;
			int visitB = -1;
			for(int i = 0;i<size;i++) {
				for(int j = 0;j<size;j++) {
					if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j]<min) {
						min = graph.weight[i][j];
						visitA = i;
						visitB = j;
					}
				}
			}
			visited[visitB] = 1;
			System.out.println("边<"+graph.data[visitA]+","+graph.data[visitB]+">权值："+min);
		}
	}
	
	
}


class MGraph {
	int vertes; //表示图的结点数
	char[] data; // 存放结点数据
	int[][] weight; // 存放边，也就是矩阵
	public MGraph(int vertes) {
		this.vertes = vertes;
		data = new char[vertes];
		weight = new int [vertes][vertes];
	}
}
