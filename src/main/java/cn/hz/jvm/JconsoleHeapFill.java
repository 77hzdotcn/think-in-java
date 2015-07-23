package cn.hz.jvm;

import java.util.ArrayList;
import java.util.List;

public class JconsoleHeapFill {
	
	static class OOMOjbect{
		public byte[] placeholder = new byte[64 * 1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMOjbect> list = new ArrayList<JconsoleHeapFill.OOMOjbect>();
		for(int i = 0; i < num; i++){
			Thread.sleep(50);
			list.add(new OOMOjbect());
		}
		System.gc();
	}

	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);

	}

}
