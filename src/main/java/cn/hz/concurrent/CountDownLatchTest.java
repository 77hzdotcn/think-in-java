package cn.hz.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxf
 * @date 2017年3月3日
 * 
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		Random rand = new Random();
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(10);

		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					try {
						start.await();
						int i = rand.nextInt(10);
						TimeUnit.SECONDS.sleep(i);
						System.out.println("ok : " + i);
						end.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
		start.countDown();
		end.await();
		System.out.println("end");

	}

}
