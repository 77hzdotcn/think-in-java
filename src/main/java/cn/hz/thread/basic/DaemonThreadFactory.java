package cn.hz.thread.basic;

import java.util.concurrent.ThreadFactory;

/**
 * 后台线程工厂
 * 
 * @author wangxf
 *
 */
public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
