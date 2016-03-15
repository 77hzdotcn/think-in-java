package cn.hz.test.my;

import java.util.ArrayList;
import java.util.List;

public class InnerClassTest {
	
	private int flag = 1;
	
	class Inner1{
		
		public void m(){
			System.out.println(flag);
		}
	}
	
	static class Inner2{
		
		public void m(){
		}
	}
	
	public static void main(String[] args){
		InnerClassTest test = new InnerClassTest();
		test.new Inner1();
		new InnerClassTest.Inner2();
		List<?>[] arr = new ArrayList<?>[2]; 
	}

}
