package cn.mars.rpc.service;

import cn.mars.rpc.api.ByeService;

/**
 * Description：
 * Created by Mars on 2020/2/7.
 */
public class ByeServiceImpl implements ByeService {

    @Override
    public String sayBye(String name) {
        return name +" bye bye";
    }
}
