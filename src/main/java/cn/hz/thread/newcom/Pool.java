package cn.hz.thread.newcom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {

	private int size;
	private List<T> items = new ArrayList<T>();
	private volatile boolean[] checkOut;
	private Semaphore available;

	public Pool(Class<T> classObject, int size) {
		this.size = size;
		checkOut = new boolean[size];
		available = new Semaphore(size, true);
		for(int i = 0; i < size; i++){
			try {
				items.add(classObject.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public T checOut() throws InterruptedException{
		available.acquire();
		return getItem();
	}
	
	public void checkIn(T x){
		if(releaseItem(x)){
			available.release();
		}
	}
	
	public synchronized T getItem(){
		for(int i = 0; i < size; i++){
			if(!checkOut[i]){
				checkOut[i] = true;
				return items.get(i);
			}
		}
		return null;
	}
	
	public synchronized boolean releaseItem(T item){
		int index = items.indexOf(item);
		if(index < 0){
			return false;
		}
		if(checkOut[index]){
			checkOut[index] = false;
			return true;
		}
		return false;
	}

}
