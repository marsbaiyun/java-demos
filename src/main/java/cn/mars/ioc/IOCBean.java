package cn.mars.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Description：
 * Created by Mars on 2020/2/11.
 */
public class IOCBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean,
        DisposableBean {

    public int num = 1;
    public String message = "message";
    public String content = new String("content");

    public IOCBean() {
        System.out.println("constructMethod");
    }

    @Override
    public String toString() {
        return "IOCBean{" +
                "num=" + num +
                ", message='" + message + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }


    public void init() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("before " + methodName + " : " + this);
        this.content = "initContent";
        System.out.println(methodName + " done : " + this);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+": "+ this);
    }

    public void destroy() throws Exception {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("before " + methodName + " : " + this);
        System.out.println(methodName);
    }
}
