package tenalgorithms;
import java.awt.Point;
import java.util.*;
public class HorseChessBoard {
	private static int X;
	private static int Y;
	private static boolean visited[];
	private static boolean finished;
	public static void main(String[] args) {
		X = 8;
		Y = 8;
		int row = 1;
		int column = 1;
		int [][] chessBoard = new int [X][Y];
		visited = new boolean[X*Y];
		long start = System.currentTimeMillis();
		traversalChessBoard(chessBoard,row-1,column-1,1);
		long end = System.currentTimeMillis();
		System.out.println("Time taken="+(end-start));
		
		for(int[] rows:chessBoard) {
			for(int step:rows) {
				System.out.print(step+"\t");
			}
			System.out.println();
		}
	}

	public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step) {
		chessBoard[row][column] = step;
		visited[row * X + column] = true;
		ArrayList<Point> ps = next(new Point(column, row)); // 可走的路
		sort(ps);
		while (!ps.isEmpty()) {
			Point p = ps.remove(0);
			// 判断该点是否已经访问过
			if (!visited[p.y * X + p.x]) {
				traversalChessBoard(chessBoard, p.y, p.x, step + 1);
			}
		}
		if (step < X * Y && !finished) {
			chessBoard[row][column] = 0;
			visited[row * X + column] = false;
		} else {
			finished = true;
		}
	}
	
	public static ArrayList<Point> next(Point curPoint){
		ArrayList<Point> ps = new ArrayList<>();
		Point p1 = new Point();
		if((p1.x = curPoint.x - 2)>=0 && (p1.y = curPoint.y-1)>=0) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x - 1)>=0 && (p1.y = curPoint.y-2)>=0) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y-2)>=0) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y-1)>=0) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y+1)<Y) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y+2)<Y) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y+2)<Y) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y+1)<Y) {
			ps.add(new Point(p1));
		}
		return ps;
	}
	
	public static void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {
			
			@Override
			public int compare(Point o1,Point o2) {
				int count1 = next(o1).size();
				int count2 = next(o2).size();
				if(count1<count2) {
					return -1;
				}else if(count1==count2) return 0;
				else return 1;
			}
		});
	}
}
