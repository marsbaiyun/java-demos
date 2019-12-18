package cn.mars.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description：jdk动态代理
 * Created by Mars on 2018/4/25.
 */
public class ProxyFactory implements InvocationHandler {

    private Class<?> target;
    private Object real;
    //委托类class
    public ProxyFactory(Class<?> target){
        this.target=target;
    }

    //实际执行类bind
    public  Object bind(Object real){
        this.real=real;
        //利用JDK提供的Proxy实现动态代理
        return Proxy.newProxyInstance(target.getClassLoader(),new Class[]{target},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理环绕
        System.out.println("begin");
        //执行实际的方法
        Object invoke = method.invoke(real, args);
        System.out.println("end");
        return invoke;
    }

    public static void main(String[] args) {
        Calculator proxy =(Calculator) new ProxyFactory(Calculator.class).bind(new Calculator.CalculatorImpl());
        proxy.add(1,2);
    }
}
