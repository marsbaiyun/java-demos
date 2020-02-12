package cn.mars.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description：
 * Created by Mars on 2020/2/11.
 */
@Configuration
public class IOCConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public IOCBean iocBean() {
        return new IOCBean();
    }
}
