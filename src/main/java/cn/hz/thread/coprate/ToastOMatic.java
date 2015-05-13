package cn.hz.thread.coprate;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import cn.hz.thread.coprate.Toast.Status;

public class ToastOMatic {

	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(),
				butteredQueue = new ToastQueue(),
				finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED
	}

	private Status status = Status.DRY;

	private final int id;

	public Toast(int id) {
		this.id = id;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public int getId() {
		return id;
	}
	
	public Status getStatus(){
		return status;
	}

	@Override
	public String toString() {
		return "Toast " + id + " " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {

	private int count;

	private ToastQueue tq;

	private Random rand = new Random(47);

	public Toaster(ToastQueue tq) {
		this.tq = tq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
				tq.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster Off");
	}

}

class Butterer implements Runnable {

	private ToastQueue dryQuery, butterQueue;

	public Butterer(ToastQueue dryQuery, ToastQueue butterQueue) {
		this.dryQuery = dryQuery;
		this.butterQueue = butterQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = dryQuery.take();
				t.butter();
				System.out.println(t);
				butterQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Butterer interrupted");
		}
		System.out.println("Butterer Off");
	}

}

class Jammer implements Runnable {

	private ToastQueue butterQueue, finishedQueue;

	public Jammer(ToastQueue butterQueue, ToastQueue finishedQueue) {
		this.butterQueue = butterQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = butterQueue.take();
				t.jam();
				System.out.println(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Jammer interrupted");
		}
		System.out.println("Jammer Off");
	}

}

class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;
	
	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if(t.getId() != counter++ || t.getStatus() != Status.JAMMED){
					System.out.println(">>>Error: " + t);
				}else{
					System.out.println("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater Off");
	}
	
}