package cn.hz.test.my;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import cn.hz.my.MultiTasksExecutor;
import cn.hz.my.MultiTasksExecutor.Task;

public class AsynTaskTest {
	
	Set<Task> tasks = new HashSet<Task>();
	final Random rand = new Random();

	StopWatch sw = new StopWatch(); 
	
	class Task1 extends Task<String> {
		private Random r = rand;
		private String result = "";

		@Override
		public void execute() {
			try {
				System.out.println("bit: " + r.nextInt(10));
				TimeUnit.SECONDS.sleep(1);
				System.out.println("1");
				result = "Hello World";
			} catch (InterruptedException e) {
			}
		}

		@Override
		public String result() {
			return result;
		}

	}

	class Task2 extends Task<Integer> {

		@Override
		public void execute() {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("2");
			} catch (InterruptedException e) {
			}
		}

		@Override
		public Integer result() {
			return 2;
		}

	}

	class Task3 extends Task {

		@Override
		public void execute() {
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("3");
			} catch (InterruptedException e) {
			}
		}

		@Override
		public String result() {
			return null;
		}

	}
	
	class Task4 extends Task {
		private int i;
		
		public Task4(int i){
			this.i = i;
		}

		@Override
		public void execute() {
			try {
				TimeUnit.SECONDS.sleep(3);
				i++;
			} catch (InterruptedException e) {
			}
		}

		@Override
		public Object result() {
			return null;
		}
		
	}

	@Before
	public void ready() {
		tasks.add(new Task1());
		tasks.add(new Task2());
		tasks.add(new Task3());
	}

	@Test
	public void test1SynExecute() throws InterruptedException {
		sw.start();
		new MultiTasksExecutor(tasks, false).execute();
		sw.stop();
		System.out.println("同步执行任务测试,待所有执行完才返回 Time: " + sw.getTotalTimeMillis());
	}

	@Test
	public void test2AsynExecuteWait() throws InterruptedException {
		sw.start();
		new MultiTasksExecutor(tasks).execute();
		sw.stop();
		System.out.println("异步执行任务测试,待所有执行完才返回 Time: " + sw.getTotalTimeMillis());
	}

	@Test
	public void test3AsynExecuteNoWait() throws InterruptedException {
		sw.start();
		new MultiTasksExecutor(tasks, true, false).execute();
		sw.stop();
		System.out.println("异步执行任务测试,任务执行完以前已经返回" + sw.getTotalTimeMillis());
	}

}
