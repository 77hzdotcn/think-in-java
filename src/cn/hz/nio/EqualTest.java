package cn.hz.nio;

public class EqualTest {

	public static void main(String[] args) {
		short v1 = 18;
		Long v2 = new Long("18");
		Long v3 = new Long(18);
		Short v4 = new Short(v1);
		System.out.println(v1 == v4);
		System.out.println(v1 == v2);
		System.out.println(v1 == v3);
		System.out.println(v2 == v3);
		System.out.println(v3.equals(v1));
		System.out.println(v3.equals(v4));
		System.out.println("======================");
		int a1=1;
	    int a2=1;
	    Integer b1 =new Integer (1);
	    Integer b2 =new Integer (1);
		System.out.println(a1 == b1);
		System.out.println(b1 == b2);

	}

}
