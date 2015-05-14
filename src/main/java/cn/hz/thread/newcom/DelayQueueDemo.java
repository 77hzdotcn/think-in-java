package cn.hz.thread.newcom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		for(int i = 0; i < 20; i ++){
			queue.add(new DelayedTask(rand.nextInt(5000)));
		}
		queue.add(new DelayedTask.EndSentinel(rand.nextInt(5000), exec));
		exec.execute(new DelayedTaskConsumer(queue));

	}

}

class DelayedTask implements Delayed, Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final long trigger;
	private final long delta;
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayinMillionseconds) {
		this.delta = delayinMillionseconds;
		trigger = System.nanoTime()
				+ TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}

	@Override
	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask) o;
		if (this.trigger < that.trigger) {
			return -1;
		}
		if (this.trigger > that.trigger) {
			return 1;
		}
		return 0;
	}

	@Override
	public void run() {
		System.out.print(this);

	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public String toString() {
		return String.format("[%1$-4d] Task %2$s", delta, id);
	}

	public String summary() {
		return "(" + id + ":" + delta + ")";
	}

	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;

		public EndSentinel(int delayinMillionseconds, ExecutorService exec) {
			super(delayinMillionseconds);
			this.exec = exec;
		}

		@Override
		public void run() {
			for (DelayedTask task : sequence) {
				System.out.println(task.summary() + " ");
			}
			System.out.println();
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}

	}

}

class DelayedTaskConsumer implements Runnable{
	private DelayQueue<DelayedTask> q;
	
	public DelayedTaskConsumer(DelayQueue<DelayedTask> q){
		this.q = q;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				q.take().run();
			}
		} catch (InterruptedException e) {
		}
		System.out.println("Finished DelayedTaskConsumer");
	}
	
}