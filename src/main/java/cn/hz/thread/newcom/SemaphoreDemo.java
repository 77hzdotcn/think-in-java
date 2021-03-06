package cn.hz.thread.newcom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
	final static int SIZE = 25;

	public static void main(String[] args) throws InterruptedException {
		final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < SIZE; i++){
			exec.execute(new CheckoutTask(pool));
		}
		System.out.println("All checkout tasks created");
		List<Fat> list = new ArrayList<Fat>();
		for(int i = 0; i < SIZE; i++){
			Fat f = pool.checOut();
			System.out.println(i + ": main() thread checked out");
			f.operation();
			list.add(f);
		}
		Future<?> blocked = exec.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					pool.checOut();
				} catch (InterruptedException e) {
					System.out.println("Checkout() interrupted");
				}
			}
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);
		System.out.println("check in objects in " + list);
		for(Fat f : list){
			pool.checkIn(f);
		}
		for(Fat f : list){
			pool.checkIn(f);
		}
		exec.shutdown();
	}

}

class CheckoutTask<T> implements Runnable{
	
	private static int counter = 0;
	private final int id = counter++;
	private Pool<T> pool;
	
	public CheckoutTask(Pool<T> pool){
		this.pool = pool;
	}

	@Override
	public void run() {
		try {
			T item = pool.checOut();
			System.out.println(this + " check out " + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this + " check in " + item);
			pool.checkIn(item);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "CheckoutTask " + id;
	}
	
}
