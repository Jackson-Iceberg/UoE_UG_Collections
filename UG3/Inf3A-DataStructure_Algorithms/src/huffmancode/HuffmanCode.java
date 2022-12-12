package huffmancode;
import java.io.*;
import java.util.* ;
public class HuffmanCode {
	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
//		System.out.println(Arrays.toString(contentBytes));
//		System.out.println(contentBytes.length); // 40
		byte[] huffmanCodeBytes = huffmanZip(contentBytes);
		System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes)+huffmanCodeBytes.length); // 17 (40-17)/40 压缩率57%
//		System.out.println(bytesToBitString((byte)-1));
		byte[] sourceBytes = decode(huffmanCodes,huffmanCodeBytes);
		System.out.println("原来的字符串="+new String(sourceBytes));
//		List<Node> nodes = getNodes(contentBytes);
//		System.out.println("nodes="+nodes);
//		
//		// 测试创建的二叉树
//		System.out.println("赫夫曼树");
//		Node huffmanTreeRoot = createHuffmanTree(nodes);
//		System.out.println("前序遍历");
//		huffmanTreeRoot.preOrder();
//		
//		// 测试是否生成了对应的哈夫曼编码
//		getCodes(huffmanTreeRoot);
//		System.out.println("生成的哈夫曼编码表"+huffmanCodes);
//		
//		byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
	}
	
	/**
	 * 
	 * @param srcFile 你传入的压缩文件
	 * @param dstFile 压缩后文件放哪
	 */
	// 编写一个方法，将一个文件进行压缩
	public static void zipFile(String srcFile,String dstFile) {
		// 创建输出流
		OutputStream os = null;
		ObjectOutputStream oos = null;
		FileInputStream is = null;
		try {
			// 创建文件的输入流
			is = new FileInputStream(srcFile);
			// 创建一个和源文件一样大的byte[]
			byte[] b = new byte[is.available()];
			// 读取文件
			is.read(b);
			// 直接对源文件压缩
			byte[] huffmanBytes = huffmanZip(b);
			// 创建文件的输出流，存放压缩文件
			os = new FileOutputStream(dstFile);
			// 创建一个和文件输出流关联的ObjectOutputStream
			oos = new ObjectOutputStream(os);
			// 把赫夫曼编码后的字节数组写入压缩文件
			oos.writeObject(huffmanBytes);
			// 以对象流的方式写入赫夫曼编码，是为了以后恢复源文件时使用
			// 注意一定要把赫夫曼编码写入压缩文件
			oos.writeObject(huffmanCodes);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				is.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	// 编写一个方法，完成对压缩文件的解压
	/**
	 * 
	 * @param zipFile 需要压缩的文件
	 * @param dstFile 将文件压缩到哪个路径
	 */
	public static void unZipFile(String zipFile,String dstFile) {
		// 定义文件输入流
		InputStream is = null;
		// 定义一个对象输入流
		ObjectInputStream ois = null;
		// 定义文件的输出流
		OutputStream os = null;
		try {
			// 创建文件输入流
			is = new FileInputStream(zipFile);
			// 创建一个和is关联的对象输入流
			ois = new ObjectInputStream(is);
			// 读取bytes 数组 huffmanBytes
			byte[] huffmanBytes = (byte[]) ois.readObject();
			// 读取哈夫曼编码表
			Map<Byte,String> huffmanCodes = (Map<Byte,String>) ois.readObject();
			// 解码
			byte[] bytes = decode(huffmanCodes,huffmanBytes);
			// 将bytes数组写入到目标文件
			os = new FileOutputStream(dstFile);
			// 写数据到dstFile文件
			os.write(bytes);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {				
				os.close();
				ois.close();
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	// 编写一个方法，完成对压缩数据的解码
	private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes) {
		// 1. 先得到huffmanBytes对应的二进制的字符串，形式1010100010111
		StringBuilder stringBuilder = new StringBuilder();
		// 将bytes数组转成二进制的字符串
		for(int i = 0;i<huffmanBytes.length;i++) {
			byte b = huffmanBytes[i];
			// 判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length-1);
			stringBuilder.append(bytesToBitString(!flag,b));
		}
		
		// 把字符串安装制定的赫夫曼编码进行解码
		// 把赫夫曼编码进行调换，因为反向查询 a-> 100,100->a
		Map<String,Byte> map = new HashMap<String,Byte>();
		for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		//创建要给集合，存放byte
		List<Byte> list = new ArrayList<>();
		// i 可以理解成索引，扫描stringBuilder
		for(int i = 0;i<stringBuilder.length();) {
			int count = 1; // 小的计时器
			boolean flag = true;
			Byte b = null;
			while(flag) {
				//递增第取出key
				String key = stringBuilder.substring(i,i+count);
				b = map.get(key);
				if(b == null) { // 说明没有匹配到
					count++;
				}else {//匹配到
					flag = false;
				}
			}
			list.add(b);
			i+=count;		
		}
		// 当for循环结束后，list就存放了所有的字符
		// 把list中的数据放入到byte【】并返回
		byte b[] = new byte[list.size()];
		for(int i = 0;i<b.length;i++) {
			b[i] = list.get(i);
		}
		return b;
	}
	
	
	
	// 完成数据的解压
	// 思路
	// 1. 将huffmanCodeBytes[-88,-65,-56,-65,...]
	// 重写转成 赫夫曼编码对应的二进制的字符串“1010100010111...”
	// 2. 赫夫曼编码 对应的二进制的字符串“1010100010111...” =》 对照 赫夫曼编码 转回 "i like like like java do you like a java";
	
	/**
	 * 将一个byte转成一个二进制的字符串
	 * @param b
	 * @param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false表示不需要补，最后一个字节不需要补高位
	 * @return
	 */
	private static String bytesToBitString(boolean flag,byte b) {
		// 使用变量保存b
		int temp = b; // 将b转成int
		if(flag) {			
			temp |= 256;
		}
		String str = Integer.toBinaryString(temp); // 返回的是temp对应的二进制补码
//		System.out.println("str="+str);
		if(flag) {			
			return str.substring(str.length()-8);
		}
		else return str;
	}
	
	// 把方法包一块了，来调用
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = getNodes(bytes);
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		getCodes(huffmanTreeRoot);
		byte[] huffmanCodeBytes = zip(bytes,huffmanCodes);
		return huffmanCodeBytes;
		
	}
	
	
	// 编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
	/**
	 * 
	 * @param bytes 为原始的字符串对应的byte【】
	 * @param huffmanCodes 生成的赫夫曼编码map
	 * @return 赫夫曼编码 压缩后的byte[]
	 * 举例：String content = "i like like like java do you like a java" => byte[] contentBytes = content.getBytes()
	 * 返回的是字符串“1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
	 * =>对应的byte[]buffmanCodeBytes,即8位对应一个byte，放入到huffmanCodeBytes
	 * huffmanCodeBytes[0] = 10101000（补码） =》 byte【推导10101000 =》 10101000-1 =》 10100111（反码） =》11011000=-88】
	 * huffmanCodeBytes[1] = -88
	 */
	private static byte[] zip(byte[]bytes,Map<Byte,String> huffmanCodes) {
		// 1. 利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		// 遍历bytes数组
		for(byte b:bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
//		System.out.println("测试stringBuilder="+stringBuilder.toString());
		int len = 0;
		if(stringBuilder.length()%8==0) len = stringBuilder.length()/8;
		else len = stringBuilder.length()/8+1;
		
		String strByte;
		// 创建 存储压缩后的byte数组
		byte[] huffmanCodeByte = new byte[len];
		int index = 0; //记录是第几个byte
		for(int i = 0;i<stringBuilder.length();i+=8) {
			if(i+8>stringBuilder.length()) {
				strByte = stringBuilder.substring(i, stringBuilder.length());
			}else {
				strByte = stringBuilder.substring(i, i+8);
			}
			//将strByte 转成一个byte，放入到huffmanCodeBytes
			huffmanCodeByte[index] = (byte)Integer.parseInt(strByte,2);
			index++;
		}
		return huffmanCodeByte;
	
	}
	
	// 生成Huffman树对应的赫夫曼编码
	// 思路
	// 1. 将赫夫曼编码表存放在Map<Byte,String>形式
	// 32 -> 01, 977->100, 100->11000等等形式
	static Map<Byte,String> huffmanCodes = new HashMap<>();
	// 2. 在生成赫夫曼编码时，需要去拼接路径，定义一个StringBuilder存储某个叶子结点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	
	// 为了调用方便，重载getCodes
	private static Map<Byte,String> getCodes(Node root){
		if(root==null) return null;
		// 处理root的左子树
		getCodes(root.left,"0",stringBuilder);
		// 处理root的右子树
		getCodes(root.right,"1",stringBuilder);
		return huffmanCodes;
	}
	
	
	/**
	 * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合中
	 * @param node 传入结点
	 * @param code 路径：左子结点是0，右子结点是1
	 * @param stringBuilder 用于拼接路径
	 */
	private static  void getCodes(Node node,String code,StringBuilder stringBuilder) {
		StringBuilder sb2 = new StringBuilder(stringBuilder);
		// 将code加入到 strinbuilder2中
		sb2.append(code);
		if(node != null) {//如果node==null 不处理
			// 判断当前node是叶子结点还是非叶子结点
			if(node.data == null) {// 非叶子结点
				//递归处理
				// 向左递归
				getCodes(node.left,"0",sb2);
				// 向右
				getCodes(node.right,"1",sb2);
			}else {//说明是叶子结点
				// 表示找到了某个叶子结点的最后
				huffmanCodes.put(node.data, sb2.toString());
			}
		}
	}
	
	private static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("Huffman树为空，不能遍历");
		}
	}
	
	
	private static List<Node> getNodes(byte[] bytes){
		// 1 创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		// 遍历bytes，统计每个bytes出现的次数-> map[key,value]
		Map<Byte,Integer> counts = new HashMap<>();
		for(byte b:bytes) {
			Integer count = counts.get(b);
			if(count == null) {
				counts.put(b,1);
			}else {
				counts.put(b, count+1);
			}
		}
		// 把每一个键值对转成一个node对象，并加入到Nodes集合
		for(Map.Entry<Byte,Integer> entry:counts.entrySet()) {
			nodes.add(new Node(entry.getKey(),entry.getValue()));
		}
		return nodes;
	}
	
	// 可以通过List 创建对应的赫父曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		while(nodes.size()>1) {
			// 排序 从小到大
			Collections.sort(nodes);
			// 取出第一颗最小的二叉树
			Node leftNode = nodes.get(0);
			// 取出第二颗最小的二叉树
			Node rightNode = nodes.get(1);
			// 创建一颗新的二叉树，他的根结点没有data，只有权值
			Node parent = new Node(null,leftNode.weight+rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			
			// 将已经处理好的两颗二叉树从nodes删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// 将新的二叉树加入到nodes
			nodes.add(parent);
		}
		return nodes.get(0);
	}
	
}

//创建Node，带数据和权值
class Node implements Comparable<Node>{
	Byte data; // 存放数据（字符）本身，比如‘a' =》 97， “ ” 空格 = 32
	int weight; // 权值，表示字符出现的次数
	Node left;
	Node right;
	
	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight-o.weight;
	}
	
	// 前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left!=null) {
			this.left.preOrder();
		}
		if(this.right!=null) {
			this.right.preOrder();
		}
	}
}
