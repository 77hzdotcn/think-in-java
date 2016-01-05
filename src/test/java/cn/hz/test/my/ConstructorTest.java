package cn.hz.test.my;

import org.junit.Test;

public class ConstructorTest {

	@Test
	public void test(){
//		Assert.assertNull(new A(true).getA());
//		String[] a = {"1", "2"};
//		System.out.println(Arrays.asList(a) + "");
		C c = new C();
		c.setA("wangxf");
		System.out.println(((B)c).getA());
	}
	
}


class A {
	private String a = "wangxf";
	
	public A(){}
	
	public A(boolean clear){
		this();
		if(clear){
			clearAValue();
		}
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
	void clearAValue(){
		this.a = null;
	}
	
}

class B {
	
	private String a;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
}

class C extends B {
	
	private String a;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
}