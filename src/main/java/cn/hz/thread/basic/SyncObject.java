package cn.hz.thread.basic;

public class SyncObject {

	public static void main(String[] args) {
		final DualSync ds = new DualSync();
		new Thread(){
			public void run() {
				ds.f();
			};
		}.start();
		ds.g();

	}

}

class DualSync {
	private Object synObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}
	public void g(){
		synchronized (synObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}
