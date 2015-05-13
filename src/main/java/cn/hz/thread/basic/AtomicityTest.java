package cn.hz.thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {
	private int i = 0;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}

	}

	@Override
	public void run() {
		while (true) {
			evenIncreament();
		}

	}

	public int getValue() {
		return i;
	}

	public synchronized void evenIncreament() {
		i++;
		i++;
	}

}
