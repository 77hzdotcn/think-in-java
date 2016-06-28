/**
 * 
 */
package cn.hz.test.my;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxf
 * @date 2016年4月23日
 *
 */
public class ThreadPoolAndThreadLocal {

	private void test1() throws InterruptedException {
		ContextHolder.set(1);
		Executor.execut(new Runnable() {

			@Override
			public void run() {
				print();

			}
		});
		TimeUnit.SECONDS.sleep(1);
		ContextHolder.set(3);
		Executor.execut(new Runnable() {

			@Override
			public void run() {
				print();

			}
		});
		TimeUnit.SECONDS.sleep(1);
	}

	private void test2() throws InterruptedException {
		ContextHolder.set(2);
		Executor.execut(new Runnable() {

			@Override
			public void run() {
				print();

			}
		});
		TimeUnit.SECONDS.sleep(1);
	}

	private void print() {
		System.out.println(String.format("child t[%s] : %d", Thread.currentThread().getName(), ContextHolder.get()));
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolAndThreadLocal test = new ThreadPoolAndThreadLocal();
		test.test1();
		test.test2();
	}

}

class ContextHolder {

	private static InheritableThreadLocal<Integer> holder = new InheritableThreadLocal<Integer>();

	public static Integer get() {
		return holder.get();
	}

	public static void set(Integer value) {
		holder.set(value);
	}

	public static void remove() {
		holder.remove();
	}
}

class Executor {

	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public static void execut(Runnable r) {
		executor.execute(r);
	}

	public void destory() {
		executor.shutdown();
	}
}