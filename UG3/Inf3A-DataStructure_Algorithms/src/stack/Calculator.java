package stack;

public class Calculator {
	public static void main(String[] args) {
		// 例子：3+2*6-2 = 13
		String expression = "30+2*6-2";
		// 创建两个栈，一个数字栈，一个符号栈
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		// 定义所需的相关变量
		int index = 0; // 用于扫描
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' ';
		char [] charList = expression.toCharArray();
		String combineNum = "";
		
		
		//开始while循环地扫描expression
		while(index<=charList.length-1) {
			ch = charList[index];
			//判断ch是什么，然后决定放进哪个栈
			if(operStack.isOper(ch)) {//如果是运算符
				//判断符号栈是否为空，为空直接入栈
				if(operStack.isEmpty()) {
					operStack.push(ch);
				}
				else {//判断优先级
					//如果当今操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数
					//符号栈pop一个符号，进行运算，得到结果，入数栈，然后将当今的操作符入符号栈
					if(operStack.priority(ch)<=operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						//得到结果，入数栈
						numStack.push(res);
						// 将当今的操作符入符号栈
						operStack.push(ch);
					}
					// 该符号优先级大于栈中符号优先级，直接入栈
					else {//直接入符号栈
						operStack.push(ch);
					}
				}
				
				
			}else {//数字直接入栈
				// 不适用与多位数 得改进
//				numStack.push(ch-48);
				// 1. 当处理多位数时，不能发现一个数就立即入栈，可能是多位数
				// 2. 检查下一位是否为数，为数则拼接
				combineNum += ch;
				if(index == charList.length-1) {
					numStack.push(Integer.parseInt(combineNum));
				}else {	
					if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))) {
						numStack.push(Integer.parseInt(combineNum));
						combineNum = "";
					}
				}
				
			}
			index++;
		}
		
		// 当表达式扫描完毕，顺序地从数栈和符号栈中pop出相应的数和符号，并运行
		while(true) {
			//如何符号栈为空，则计算最后的结果
			if(operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res =  numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		// 数栈最后只剩下一个数，该数为结果
		//输出结果
		System.out.printf("表达式 %s = %d",expression,numStack.pop());
		
		
	
	
	
	
	}
	
}






//先创建一个栈
//定义一个ArrayStack2来表示栈
class ArrayStack2{
	private int maxSize; // 栈的大小
	private int[] stack; // 数组，数组模拟栈，数据就放在该数组内
	private int top = -1; // top表示栈顶，初始化为-1
	
	// 构造器
	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		this.stack = new int [maxSize];
	}
	
	//栈满
	public boolean isFull() {
		return top == maxSize-1;
	}
	
	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}
	//入栈 -push
	public void push(int value) {
		//先判断栈是否满
		if(isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	//出栈 -pop
	public int pop() {
		//先判断栈是否空
		if(isEmpty()) {
			throw new RuntimeException("栈空，没有数据");
		}
		int value = stack[top];
		top--;
		return value;
	}
	// 返回栈顶的值，但不是pop
	public int peek() {
		return stack[top];
	}
	
	
	//显示栈的情况【遍历栈】，遍历时，需要从栈顶开始显示数据
	public void list() {
		if(isEmpty()) {
			System.out.println("栈空，没有数据");
			return;
		}
		// 需要从栈顶开始显示数据
		for(int i = top;i >= 0;i--) {
			System.out.printf("stack[%d]=%d\n",i,stack[i]);
		}
	}
	// 返回运算符的优先级，优先级是由程序员来确定，优先级使用数字来表示
	// 数字越大，则优先级越高
	public int priority(int oper) {
		if(oper == '*' || oper == '/'){
				return 1;
	}else if (oper == '+' || oper == '-') {
		return 0;
	}else{
		return -1; // 假定目前的表达式只有+-*/
	}
}
	// 判断是不是一个运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}
	// 计算
	public int cal(int num1,int num2,int oper) {
		int res = 0; //用于存放结果
		switch (oper) {
		case '+':
			res = num2+num1;
			break;
		case '-':
			res = num2-num1;
			break;
		case '*':
			res = num2*num1;
			break;
		case '/':
			res = num2/num1;
			break;
		default:
			break;
		}
		return res;
	}
	
	
	
}