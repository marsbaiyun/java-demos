package cn.mars.rpc.service;

import cn.mars.rpc.api.HelloService;

/**
 * Description：
 * Created by Mars on 2020/2/7.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return name + " say hello";
    }
}
