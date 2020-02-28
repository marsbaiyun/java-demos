package cn.mars.aspect.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Description：
 * Created by Mars on 2020/2/25.
 */
@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**");
    }
}
