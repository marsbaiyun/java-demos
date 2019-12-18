package cn.mars.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description：
 * Created by Mars on 2018/4/25.
 */
public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Calculator.class);
        enhancer.setCallback(new MethodInterceptor() {
            //类似invokerhanddler的invoke方法
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("begin");
                Object invoke = methodProxy.invoke(new Calculator.CalculatorImpl(), objects);
                System.out.println("end");
                return invoke;
            }
        });
        Calculator proxy =(Calculator) enhancer.create();
        proxy.add(1,2);
    }

}
