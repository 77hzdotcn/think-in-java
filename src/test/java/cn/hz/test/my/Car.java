package cn.hz.test.my;

public class Car {
	private Engine engine;
	private Tire[] tires;
	
	public Car(){}
	
	public Car(Engine engine, Tire[] tires){
		this.engine = engine;
		this.tires = tires;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Tire[] getTires() {
		return tires;
	}

	public void setTires(Tire[] tires) {
		this.tires = tires;
	}

}
