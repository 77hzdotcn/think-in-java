package cn.hz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		Hello hello = (Hello)Proxy.newProxyInstance(Hello.class.getClassLoader(),
				new Class[] { Hello.class }, new HelloHandler());
		hello.sayHell();
	}

}

interface Hello {
	void sayHell();
}

class HelloHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Hello World!");
		return null;
	}
}
