package cn.hz.jvm;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
	
	static class OOMOjbect{
	}

	public static void main(String[] args) {
		List<OOMOjbect> list = new ArrayList<HeapOOM.OOMOjbect>();
		while(true){
			list.add(new OOMOjbect());
		}

	}

}
