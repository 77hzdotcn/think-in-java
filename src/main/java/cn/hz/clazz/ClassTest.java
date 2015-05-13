package cn.hz.clazz;

public class ClassTest {

	public static void main(String[] args) {
		System.out.println(ClassTest.class == ClassTest.class);
		System.out.println(ClassTest.class);
		System.out.println(new ClassTest().getClass());
		System.out.println(ClassTest.class == new ClassTest().getClass());

	}

}
