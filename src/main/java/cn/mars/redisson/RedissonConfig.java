package cn.mars.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description：
 * Created by Mars on 2020/2/8.
 */
//@Configuration
public class RedissonConfig {

    @Value("${redisson.address}")
    private String address;
    @Value("${redisson.password}")
    private String password;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();

        config.setCodec(StringCodec.INSTANCE)
                .useSingleServer()
                .setAddress(address)
                .setPassword(password);

        return Redisson.create(config);
    }
}
