package cn.hz.test.my;

public class FinalTest {

	public static void main(String[] args) {
		Aa obj = new Aa();
		obj.setAge(0);
		obj.setAge(1);
		System.out.println(obj.getAge());

	}

}

final class Aa {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
