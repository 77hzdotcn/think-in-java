package cn.hz.test.my;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Runnable() {

			@Override
			public void run() {
				for(;;){
					Test.one();
				}
			}
		});
		service.execute(new Runnable() {

			@Override
			public void run() {
				for(;;){
					Test.two();
				}
			}
		});
		TimeUnit.SECONDS.sleep(3);
		service.shutdownNow();
	}

}
