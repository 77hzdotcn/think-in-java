package cn.hz.thread.terminate;

import java.util.concurrent.TimeUnit;

public class InterruptingIdiom {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(1310);
		t.interrupt();
	}

}

class NeedsCleanUp {

	private int id;

	public NeedsCleanUp(int id) {
		this.id = id;
		System.out.println("NeedsCleanUp " + id);
	}

	public void cleanup() {
		System.out.println("Clean Up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d = 0.0;

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				NeedsCleanUp n1 = new NeedsCleanUp(1);
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					NeedsCleanUp n2 = new NeedsCleanUp(2);
					try {
						System.out.println("Calculating");
						for (int i = 1; i < 2500000; i++) {
							d = d + (Math.PI + Math.E) / d;
						}
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}

}