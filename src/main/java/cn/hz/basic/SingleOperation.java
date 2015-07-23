package cn.hz.basic;

public class SingleOperation {

	public static void main(String[] args) {
		//参与逻辑运算时,a++表示先运算再+1,++a表示先+1再运算
//		int count = 0;
//		System.out.println(count++ == 0);
//		System.out.println(++count == 0);
//		count = 0;
//		System.out.println(++count > 0);
		
		System.out.println(true & true);

		System.out.println(true & false);
		
		Integer commId = 1;
		System.out.println(commId != null && commId > 0);
	}

}
