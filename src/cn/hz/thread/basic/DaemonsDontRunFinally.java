package cn.hz.thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程终止run方法时，不执行finally子句
 * 
 * @author wangxf
 *
 */
public class DaemonsDontRunFinally {

	public static void main(String[] args) {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
	}

}

class ADaemon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		} finally {
			System.out.println("This should always run?");
		}
	}

}
