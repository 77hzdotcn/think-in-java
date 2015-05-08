package cn.hz.thread.deadlock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadlockingDiningPhilosopher {

	public static void main(String[] args) throws IOException {
		int ponderFactor = 0;
		int size  = 3;
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for(int i = 0; i < size; i++){
			sticks[i] = new Chopstick();
		}
		for(int i = 0; i < size; i++){
			exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, ponderFactor));
		}
		System.out.println("Press 'Enter' to quit");
		System.in.read();
		exec.shutdownNow();
	}

}
