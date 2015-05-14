package cn.hz.thread.newcom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HorseRace {
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	
	public HorseRace(int nHorses, final int pause){
		barrier = new CyclicBarrier(nHorses, new Runnable() {
			
			@Override
			public void run() {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < FINISH_LINE; i++){
					sb.append("=");
				}
				System.out.println(sb.toString());
				for(Horse horse : horses){
					System.out.println(horse.tracks());
				}
				for(Horse horse : horses){
					if(horse.getStrides() >= FINISH_LINE){
						System.out.println(horse + " won!");
						exec.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		for(int i = 0; i < nHorses; i++){
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}
	
	
	public static void main(String[] args) {
		int nHorses = 7;
		int pause = 200;
		new HorseRace(nHorses, pause);
	}

}

class Horse implements Runnable {
	private static int counter = 0;
	private static Random rand = new Random(47);
	private final int id = counter++;
	private int strides = 0;
	private CyclicBarrier barrier;

	public Horse(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					strides += rand.nextInt(3);
				}
				barrier.await();
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Horse " + id;
	}
	
	public synchronized int getStrides(){
		return strides;
	}
	
	public String tracks(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < strides; i++){
			sb.append("*");
		}
		sb.append(id);
		return sb.toString();
	}

}

