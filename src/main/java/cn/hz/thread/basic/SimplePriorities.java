package cn.hz.thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 优先级
 * 
 * @author wangxf
 *
 */
public class SimplePriorities implements Runnable {

	private int countDown = 5;
	private volatile double d;
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return Thread.currentThread() + " : " + countDown;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(this.priority);
		while (true) {
			for (int i = 0; i < 1000000; i++) {
				d += (Math.PI + Math.E) / i;
				if (i % 100 == 0) {
					Thread.yield(); // 让步,具有相同优先级的其他线程可以运行了
				}
			}
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		exec.shutdown();
	}

}
