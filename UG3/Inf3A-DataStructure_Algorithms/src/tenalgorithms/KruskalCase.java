package tenalgorithms;

import java.util.Arrays;

public class KruskalCase {
	private int edgesNum;
	private char[] vertexs;
	private int[][] matrix;
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		char[] vertexs = {'A','B','C','D','E','F','G'};
		int[][] matrix = {
				{ 0, 12, INF, INF, INF, 16, 14 }, 
				{ 12, 0, 10, INF, INF, 7, INF }, 
				{ INF, 10, 0, 3, 5, 6, INF },
				{ INF, INF, 3, 0, 4, INF, INF }, 
				{ INF, INF, 5, 4, 0, 2, 8 }, 
				{ 16, 7, 6, INF, 2, 0, 9 },
				{ 14, INF, INF, INF, 8, 9, 0 },
				};
		KruskalCase kruskalCase = new KruskalCase(vertexs,matrix);
		kruskalCase.print();
		kruskalCase.kruskal();
		
	}
	public KruskalCase(char[] vertes,int[][] matrix) {
		this.vertexs = vertes;
		this.matrix = matrix;
		// 统计边
		for(int i = 0;i<vertes.length;i++) {
			for(int j = i+1;j<vertes.length;j++) {
				if(matrix[i][j] != INF) {
					edgesNum++;
				}
			}
		}
	}
	public void kruskal() {
		int index = 0;
		int [] ends = new int[edgesNum];
		EData[] results = new EData[edgesNum];
		EData[] edges = getEdges();
		System.out.println("图的边的集合="+Arrays.toString(edges)+edges.length+"边");
		
		sortEdges(edges);
		for(int i = 0;i<edgesNum;i++) {
			int p1 = getPosition(edges[i].start);
			int p2 = getPosition(edges[i].end);
			int destination_p1 = getEnd(ends,p1);
			int destination_p2 = getEnd(ends,p2);
			if(destination_p1!=destination_p2) {
				ends[destination_p1] = destination_p2;
				results[index++] = edges[i];
			}
		}
		System.out.println("最小生成树为");
		for(int i = 0;i<index;i++) {
			System.out.println(results[i]);
		}
	}
	
	public void print() {
		System.out.println("邻接矩阵为：\n");
		for(int i = 0;i<vertexs.length;i++) {
			for(int j = 0;j<vertexs.length;j++) {
				System.out.printf("%12d\t",matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	private void sortEdges(EData[] edges) {
		for(int i = 0;i<edges.length-1;i++) {
			for(int j = 0;j<edges.length-1-i;j++) {
				if(edges[j].weight>edges[j+1].weight) {
					EData temp = edges[j];
					edges[j] = edges[j+1];
					edges[j+1] = temp;
				}
			}
		}
	}
	
	private int getPosition(char ch) {
		for(int i = 0;i<vertexs.length;i++) {
			if(vertexs[i] == ch) {
				return i;
			}
		}
		return -1;
	}
	
	private EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[edgesNum];
		for(int i = 0;i<vertexs.length;i++) {
			for(int j = i+1;j<vertexs.length;j++) {
				if(matrix[i][j] != INF) {
					edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
				}
			}
		}
		return edges;
	}
	private int getEnd(int[] ends,int i) {
		while(ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}
	
	
}
class EData{
	char start;
	char end;
	int weight;
	public EData(char start,char end,int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "EData [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}
}

