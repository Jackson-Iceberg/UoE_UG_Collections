package stack;

import java.util.*;

public class PolandNotation {
	public static void main(String[] args) {
		
		// 完成一个中缀表达式转后缀表达式的功能
		// 思路and说明
		// 1. 1+((2+3)*4)-5 => 转成 1 2 3 + 4 * + 5 -
		// 2. 因为直接对string进行操作不方便，所以先将String转成对应的list
		// 即 "1+((2+3)*4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("中缀表达式对应的List="+infixExpressionList);
		List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
		System.out.println("后缀表达式对应的List="+suffixExpressionList);
		
		// 将中缀表达式转换成后缀表达式对应的List
		// ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
		
		System.out.printf("expression=%d",calculate(suffixExpressionList));
		
		
		//先定义逆波兰表达式
		// （3+4）*5-6 =》 3 4 + 5 * 6 -
////		String suffixExpression = "30 4 + 5 * 6 -";
//		String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//		//思路
//		// 1. 先将 "3 4 + 5 * 6 - " 放进ArrayList中
//		// 2. 将ArrayList 传递给一个方法，遍历ArrayList 配合栈 完成计算
//		
//		List<String> list = getListString(suffixExpression);
//		System.out.println("rpnList="+list);
//		int res = calculate(list);
//		System.out.println("计算的结果是="+res);
	
	}
	
	// 将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
	public static List<String> getListString (String suffixExpression){
		// 将suffixExpression分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String s:split) {
			list.add(s);
		}
		return list;
	}
	
	public static int calculate(List<String> list) {
		//创建栈，只需要一个栈，数字栈即可
		Stack<String> stack = new Stack<String>();
		//遍历list
		for(String item:list) {
			// 使用正则表达式来取出数
			if(item.matches("\\d+")) { // 匹配多位数
				//入栈
				stack.push(item);
			}
			else {
				//则是运算符，那么进行运算
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if(item.equals("+")) {
					res = num1+num2;
				}
				else if(item.equals("-")) {
					res = num1-num2;
				}
				else if(item.equals("*")) {
					res = num1*num2;
				}
				else if(item.equals("/")) {
					res = num1/num2;
				}else {
					throw new RuntimeException("运算符有误");
				}
				//把res入栈
				stack.push(""+res);

			}
		}
		// 最后留在stack中的数据是运算结果
		return Integer.parseInt(stack.pop());
	}
	
	// 方法：将中缀表达式转化成对应的List
	public static List<String> toInfixExpressionList(String s){
		//定义一个List，存放中缀表达式对应的内容
		List<String> list = new ArrayList<String>();
		int i = 0; // 这是一个指针，用于遍历中缀表达式字符串
		String str; //用于多位数拼接
		char c; //每遍历到一个字符，就放入到c
		do {
			
			//如果c是一个非数字，则需要加入到list
			if((c=s.charAt(i))<48 || (c=s.charAt(i))>57){ // According to ASCII table 
				list.add(""+c);
				i++; //i需要后移，遍历
			}
			else { // 数字，考虑多位数
				str = "";
				while(i<s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <=57) {
					str += c;
					i++;
				}
				list.add(str);
			}
		}while(i<s.length());
		
		return list;
	}
	
	// 将中缀表达式转换成后缀表达式对应的List
	// ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
	public static List<String> parseSuffixExpressionList(List<String> list){
		// 定义两个栈
		Stack<String> s1 = new Stack<String>();
//		Stack<String> s2 = new Stack<String>();
		// 说明因为s2这个栈，在整个转换过程中，没有pop操作，而且后面还需要逆序输出
		// 因此比较麻烦，所以这里就不用Stack<String> 直接用ArrayList<String> s2
		List<String> s2 = new ArrayList<String>();
		
		// 遍历list
		for(String item:list) {
			// 如果是一个数字 直接入s2栈
			if(item.matches("\\d+")) {
				s2.add(item);
			}
			// 如果是运算符
			else {
				// 是左括号或者s1为空，直接添加进去
				if(item.equals("(") || s1.isEmpty()) {
					s1.push(item);
				}
				// 是右括号）则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
				else if(item.equals(")")) {
					while(!s1.peek().equals("(")) {
						s2.add(s1.pop());
					}
					s1.pop(); //将左括号弹出，消除整个小括号
				}
				else {
					// 当item的优先级小于或者等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中
					// 再次转到(4.1)与新的s1中的栈顶运算符相比较
					while(s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())) {
						s2.add(s1.pop());
					}
					// 还需要将item压入栈中
					s1.push(item);
				}
			}
		}
		// 将s1剩余的运算符依次弹出并加入s2
		while(s1.size()!= 0) {
			s2.add(s1.pop());
		}
		
		return s2; //因为是ArrayList所以正序输出即可
		
	}
	
	
	
}


class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	
	public static int getValue(String operation) {
		int result = 0;
		switch(operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("该运算符不存在");
			break;
		}
		return result;
	}
}
