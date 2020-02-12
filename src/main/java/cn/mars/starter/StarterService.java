package cn.mars.starter;

import cn.mars.StarterDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 * Created by Mars on 2020/2/9.
 */
@Service
public class StarterService {

    @Autowired
    private StarterDemoService starterDemoService;

    public void say(){
        starterDemoService.say();
    }
}
