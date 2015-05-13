package cn.hz.thread.coprate;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PipedIO {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();

	}

}

class Sender implements Runnable{
	
	private Random rand = new Random(47); 
	private PipedWriter writer = new PipedWriter();

	@Override
	public void run() {
		try{
			while(true){
				for(char c = 'A'; c < 'z'; c++){
					writer.write(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
			}
		}catch(InterruptedException e){
			System.out.println("Sender Interrupted");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public PipedWriter getWriter(){
		return writer;
	}
	
}

class Receiver implements Runnable {
	private PipedReader reader;
	
	public Receiver(Sender sender) throws IOException{
		this.reader = new PipedReader(sender.getWriter());
	}

	@Override
	public void run() {
		try{
			while(true){
				System.out.println("Read: " + (char)reader.read());
			}
		}catch(IOException e){
			System.out.println(e + " Receiver read exception");
		}
		
	}
}