/**
 * 
 */
package cn.hz.test.my;

/**
 * @author wangxf
 * @date 2016年4月24日
 *
 */
public class BitTest {

	private static final int COUNT_BITS = Integer.SIZE - 3;
	private static final int CAPACITY = (1 << COUNT_BITS) - 1;

	// runState is stored in the high-order bits
	private static final int RUNNING = -1 << COUNT_BITS;
	private static final int SHUTDOWN = 0 << COUNT_BITS;
	private static final int STOP = 1 << COUNT_BITS;
	private static final int TIDYING = 2 << COUNT_BITS;
	private static final int TERMINATED = 3 << COUNT_BITS;

	public static void main(String[] args) {
		print(1);
		print(CAPACITY);
		print(~CAPACITY);
		print(RUNNING);
		print(SHUTDOWN);
		print(STOP);
		print(TIDYING);
		print(TERMINATED);

	}

	private static void print(int value) {
		System.out.println(Integer.toBinaryString(value));
	}

}
