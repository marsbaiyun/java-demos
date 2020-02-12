package cn.mars.ioc;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;

/**
 * Description：
 * Created by Mars on 2020/2/11.
 */
public class IOCBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor, InitializingBean,
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

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+": "+ JSON.toJSONString(bean));
        return bean;
    }


    public void init() {
        this.content = "initContent";
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+": "+ this);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        Class<?> targetCls = bean.getClass();
//        Field[] targetFld = targetCls.getDeclaredFields();
//        for (Field field : targetFld) {
//            //找到制定目标的注解类
//            if (field.getName().equals("content")) {
//                try {
//                    field.set(bean, "afterContent");
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+": "+ JSON.toJSONString(bean));
        return bean;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+": "+ this);
    }

    public void destroy() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
