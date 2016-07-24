package cn.hz.test.my;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		ProxyInterface stub = (ProxyInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class[] { ProxyInterface.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println(args[0]);
						return "Success";
					}
				});
		System.out.println(stub.sayHello("Walter"));
	}

}

interface ProxyInterface {

	String sayHello(String name);
}