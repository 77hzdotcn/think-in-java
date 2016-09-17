package cn.hz.test.my;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangxf
 * @date 2016年8月6日
 * 
 */
public class CountTest {

	private static AtomicInteger index = new AtomicInteger();

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			System.out.println(index.getAndIncrement() & 5);
			TimeUnit.SECONDS.sleep(1);
		}
		
	}

}
