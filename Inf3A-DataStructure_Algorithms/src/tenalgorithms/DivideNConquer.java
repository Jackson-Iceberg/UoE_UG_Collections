package tenalgorithms;

public class DivideNConquer {
	public static void main(String[] args) {
		hanoiTower(5,'A','B','C');
	}
	
	// 解决汉诺塔问题
	public static void hanoiTower(int num,char a,char b,char c) {
		if(num == 1) {
			System.out.println("第1个盘从 "+a+"->"+c);
		}else {
			// 如果我们有n》=2情况，我们总是可以看作是两个盘，1.最下面的一个盘 2. 上面的全部盘
			// 1. 先把最上面的全部盘 A-》B，移动过程中，使用c塔作为过渡
			hanoiTower(num-1,a,c,b);
			// 2. 把最下面的盘 A-》C
			System.out.println("第"+num+"个盘从 "+a+"->"+c);
			// 3. 把b塔的所有盘从B=》C，移动过程中，使用a塔作为过渡
			hanoiTower(num-1,b,a,c);
		}
	}
}
