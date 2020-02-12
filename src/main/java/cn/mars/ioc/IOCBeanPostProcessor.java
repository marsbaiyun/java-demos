package cn.mars.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * Description：BeanPostProcessor会在IOC容器实例化要加载的每一个bean的init前后调用before和after
 * Created by Mars on 2020/2/12.
 */
@Service
public class IOCBeanPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof IOCBean){
            ((IOCBean) bean).content = "beforeContent";
            System.out.println("IOCBeanPostProcessor before : " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof IOCBean){
            ((IOCBean) bean).content = "afterContent";
            System.out.println("IOCBeanPostProcessor after : " + beanName);
        }
        return bean;
    }
}
