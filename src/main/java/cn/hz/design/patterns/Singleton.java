package cn.hz.design.patterns;

public class Singleton {
	
	private static volatile Singleton s;
	
	private Singleton(){}
	
	public static Singleton getInstance(){
		if(s == null){
			synchronized (Singleton.class) {
				if(s == null){
					s = new Singleton();
				}
			}
		}
		return s;
	}

}
