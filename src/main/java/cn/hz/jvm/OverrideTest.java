package cn.hz.jvm;

public class OverrideTest {
	
	public void sayHello(Human h){
		System.out.println("Human");
	}
	public void sayHello(Man m){
		System.out.println("Man");
	}
	public void sayHello(Women w){
		System.out.println("Women");
	}

	public static void main(String[] args) {
		Human h1 = new Man();
		Human h2 = new Women();
		OverrideTest t = new OverrideTest();
		t.sayHello(h1);
		t.sayHello(h2);
	}

}


abstract class Human{
	
	public void sayHello(){
		System.out.println("Human");
	}
}

class Man extends Human{
	@Override
	public void sayHello() {
		System.out.println("Man");
	}
}

class Women extends Human{
	@Override
	public void sayHello() {
		System.out.println("Women");
	}
}