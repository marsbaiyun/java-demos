package cn.mars.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Description：
 * Created by Mars on 2020/2/9.
 */
//@Service
public class RedisUtils {

    @Autowired
    private RedissonClient redissonClient;

    public boolean lock(String key, long time, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        if(lock.isLocked())
            return false;
        lock.lock(time, timeUnit);
        return true;
    }


}
