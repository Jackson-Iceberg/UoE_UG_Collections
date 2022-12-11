package tenalgorithms;

public class ViolenceMatch {
	public static void main(String[] args) {
		String str1 = "aabaac";
		String str2 = "aac";
		int index = violenceMatch(str1,str2);
		System.out.println("index="+index);
	}
	
	public static int violenceMatch(String str1,String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int index1 = 0;
		int index2 = 0;
		while(index1<len1 && index2<len2 ) {
			if(s1[index1] == s2[index2]) {
				index1++;
				index2++;
			}
			else {// 没有匹配成功，回溯
				index1 = index1 - (index2-1);
				index2 = 0;
			}
		}
		// 判断是否匹配成功
		if(index2 == len2) {
			return index1-index2;
		}else return -1;
		
	}
}
