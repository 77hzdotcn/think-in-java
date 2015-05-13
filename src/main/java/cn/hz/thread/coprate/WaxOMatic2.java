package cn.hz.thread.coprate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaxOMatic2 {

	public static void main(String[] args) throws InterruptedException {
		Car2 car = new Car2();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn2(car));
		exec.execute(new WaxOff2(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();

	}

}

class Car2 {
	private boolean waxOn = false;
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public void waxed(){
		lock.lock();
		try{
			waxOn = true;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void buffed(){
		lock.lock();
		try{
			waxOn = false;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void waitForWaxing() throws InterruptedException{
		lock.lock();
		try{
			while(!waxOn){
				condition.await();
			}
		}finally{
			lock.unlock();
		}
	}
	
	public void waitForBuffing() throws InterruptedException{
		lock.lock();
		try{
			while(waxOn){
				condition.await();
			}
		}finally{
			lock.unlock();
		}
	}
	
}

class WaxOn2 implements Runnable {
	private Car2 car;
	
	public WaxOn2(Car2 car){
		this.car = car;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println("Wax on!");
				TimeUnit.MICROSECONDS.sleep(500);
				car.waxed();
				car.waitForBuffing();
			}
		}catch(InterruptedException e){
			System.out.println("Exiting via interrupt.");
		}
		System.out.println("Ending Wax On task");
	}
	
}

class WaxOff2 implements Runnable{
	
	private Car2 car;
	
	public WaxOff2(Car2 car){
		this.car = car;
	}

	@Override
	public void run() {
		try{
		while(!Thread.interrupted()){
			car.waitForWaxing();
			System.out.println("Wax off!");
			TimeUnit.MILLISECONDS.sleep(300);
			car.buffed();
		}
		}catch(InterruptedException e){
			System.out.println("Exiting via interrupt.");
		}
		System.out.println("Ending Wax Off task");
	}
	
}
