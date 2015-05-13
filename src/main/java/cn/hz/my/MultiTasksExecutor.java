package cn.hz.my;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并发任务执行器,可同步、异步执行,异步执行也可选择是否在任务执行完返回,该类的设计目的是提高任务执行效率
 * 
 * @author wangxf
 *
 */
public class MultiTasksExecutor {

	private boolean isAsyn = true; // 是否异步执行
	private boolean awaitComplete = true; // 是否等待执行完成,当异步执行时起作用
	private int size; // 任务数
	private Set<Task> tasks;
	private CountDownLatch count;

	public MultiTasksExecutor(Set<Task> tasks, boolean isAsyn,
			boolean awaitComplete) {
		this.isAsyn = isAsyn;
		this.awaitComplete = awaitComplete;
		this.size = tasks.size();
		this.tasks = tasks;
		if (isAsyn && awaitComplete) {
			this.count = new CountDownLatch(size);
		}
	}

	public MultiTasksExecutor(Set<Task> tasks, boolean isAsyn) {
		this(tasks, isAsyn, true);
	}

	public MultiTasksExecutor(Set<Task> tasks) {
		this(tasks, true);
	}

	public synchronized void execute() throws InterruptedException {
		if (isAsyn) {
			executeAsyn();
		} else {
			executeSyn();
		}
	}

	private void executeAsyn() throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(size);
		for(Task t : tasks){
			t.setCount(count);
			exec.execute(t);
		}
		if (count != null) {
			count.await();
		}
		exec.shutdown();
	}

	private void executeSyn() {

		for(Task t : tasks){
			t.execute();
		}
	}

	public static abstract class Task<V> implements Runnable {

		private CountDownLatch count;

		@Override
		public void run() {
			execute();
			if (count != null) {
				count.countDown();
			}
		}

		public abstract void execute();

		public abstract V result();

		public final Task<V> setCount(CountDownLatch count) {
			this.count = count;
			return this;
		}

	}

}
