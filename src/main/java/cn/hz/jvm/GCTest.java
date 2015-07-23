package cn.hz.jvm;

public class GCTest {

	public static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		testTenuringThreshold();
	}

	/**
	 * VM参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
	 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
	 * -XX:+PrintTenuringDistribution
	 */
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[_1MB * 4];
		allocation3 = new byte[_1MB * 4];
		allocation3 = null;
		allocation3 = new byte[_1MB * 4];

	}

}
