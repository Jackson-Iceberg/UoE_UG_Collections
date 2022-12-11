package recursion;

public class MiGong {
	public static void main(String[] args) {
		//先创建一个二维数组，模拟迷宫
		//地图
		int[][] map = new int [8][7];
		// 使用1表示墙
		// 上下砌墙
		for(int i = 0;i<7;i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		// 左右砌墙
		for(int i = 0;i<8;i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		// 设置挡板
		map[3][1] = 1;
		map[3][2] = 1;
//		map[1][2] = 1;
//		map[2][2] = 1;
		//输出地图
		System.out.println("地图情况");
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<7;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		// 使用递归回溯给小球找路
		setWay(map,1,1);
		System.out.println("小球递归后的地图");
		// 输出新的地图，查看小球走过的路径，并标识过的递归
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<7;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//使用递归回溯来给小球找路
	// 思路&说明
	// 1. map表示地图
	// 2. i，j表示从地图的哪个为止开始出发
	// 3. 如果小球能到【6】【5】即底部终点，则说明通路找到，返回true
	// 4. 约定：当map【i】【j】为0表示该点没有走过；
	//		   当map【i】【j】为1表示墙
	//	   	   当map【i】【j】为2表示通路可以走
	//	   	   当map【i】【j】为3表示该点走过，但是此路不通
	// 5. 当走迷宫的时候，需要制定一个行走策略 本次策略为 下-右-上-左，如果该点走不通，再回溯
	
	public static boolean setWay(int[][]map,int i,int j) {
		if(map[6][5] == 2) { // 通路找到
			return true;
		}
		else {
			// 该点还没走过 
			if(map[i][j] == 0) {
				//按照策略 下右上左
				map[i][j] = 2;
				if(setWay(map,i+1,j)) {
					return true;
				}
				else if (setWay(map,i,j+1)) {
					return true;
				}
				else if (setWay(map,i-1,j)) {
					return true;
				}
				else if (setWay(map,i,j-1)) {
					return true;
				}
				else {
					// 说明该点是走不通的，是死路
					map[i][j] = 3;
					return false;
				}
			}
			else { // 如果map【i】【j】 ！= 0，那么可能是1，2，3，则直接return false
				return false;
			}
		}
	}
	
	
	
	
	
}
