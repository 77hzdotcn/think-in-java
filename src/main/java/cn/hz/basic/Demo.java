package cn.hz.basic;

import java.util.HashMap;
import java.util.Map;

public class Demo {

	public String a = "11";
	
	public void print(String[] strs){
		System.out.println("Array");
	}
	
	public void print(Object... strs){
		System.out.println("Var");
	}
	
	public void modifyString(String str){
		str = "222";
	}
	
	public static void main(String[] args){
		Demo demo = new Demo();
		demo.modifyString(demo.a);
		System.out.println(demo.a);
		
		Map<String, Object> map = new HashMap<>();
		for(int i = 0; i < 20; i++){
			map.put(String.valueOf(i), i);
		}
	}
}

 
