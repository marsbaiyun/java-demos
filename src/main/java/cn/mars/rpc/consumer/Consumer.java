package cn.mars.rpc.consumer;

import cn.mars.rpc.RpcFramework;
import cn.mars.rpc.api.ByeService;
import cn.mars.rpc.api.HelloService;

/**
 * Description：
 * Created by Mars on 2020/2/7.
 */
public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = RpcFramework.referenceService(HelloService.class,
                "127.0.0.1", 20880);
        System.out.println(helloService.sayHello("tom"));

        ByeService byeService = RpcFramework.referenceService(ByeService.class,
                "127.0.0.1", 20881);
        System.out.println(byeService.sayBye("jack"));
    }
}
