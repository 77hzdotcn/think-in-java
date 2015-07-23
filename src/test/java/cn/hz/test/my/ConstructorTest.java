package cn.hz.test.my;

import org.junit.Assert;
import org.junit.Test;

public class ConstructorTest {

	@Test
	public void test(){
		Assert.assertNull(new A(true).getA());
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