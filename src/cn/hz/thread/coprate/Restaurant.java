package cn.hz.thread.coprate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {

	Meal meal;

	Chef chef = new Chef(this);

	WaitPerson waitPerson = new WaitPerson(this);
	
	ExecutorService exec = Executors.newCachedThreadPool();
	
	public Restaurant(){
		exec.execute(waitPerson);
		exec.execute(chef);
	}

	public static void main(String[] args) {
		new Restaurant();

	}

}

class Meal {

	private int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}

}

class WaitPerson implements Runnable {
	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
		this.restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						wait();
					}
				}
				System.out.println("Waitperson got " + restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Waitperson interrupted");
		}

	}
}

class Chef implements Runnable {
	private Restaurant restaurant;
	
	private int count = 0;

	public Chef(Restaurant r) {
		this.restaurant = r;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this) {
					while(restaurant.meal != null){
						wait();
					}
				}
				if(count++ == 10){
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.println("Order up!");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.SECONDS.sleep(1);
			}
		}catch(InterruptedException e){
			System.out.println("Chef interrupted");
		}
	}

}
