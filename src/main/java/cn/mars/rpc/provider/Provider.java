package cn.mars.rpc.provider;

import cn.mars.rpc.RpcFramework;
import cn.mars.rpc.api.ByeService;
import cn.mars.rpc.api.HelloService;
import cn.mars.rpc.service.ByeServiceImpl;
import cn.mars.rpc.service.HelloServiceImpl;

/**
 * Description：
 * Created by Mars on 2020/2/7.
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        final HelloService helloService = new HelloServiceImpl();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcFramework.exportService(helloService, 20880);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        final ByeService byeService = new ByeServiceImpl();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcFramework.exportService(byeService, 20881);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
