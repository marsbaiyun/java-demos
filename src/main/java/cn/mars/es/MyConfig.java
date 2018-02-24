package cn.mars.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description：
 * Created by Mars on 2017/10/11.
 */
@Configuration
public class MyConfig {

    @Bean
    public TransportClient client () throws UnknownHostException {
        InetSocketTransportAddress master = new InetSocketTransportAddress(
                InetAddress.getByName("localhost"),
                9300
        );
//        InetSocketTransportAddress slave1 = new InetSocketTransportAddress(
//                InetAddress.getByName("localhost"),
//                8300
//        );
//        InetSocketTransportAddress slave2 = new InetSocketTransportAddress(
//                InetAddress.getByName("localhost"),
//                9300
//        );

        Settings settings = Settings.builder()
                .put("cluster.name", "wali")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(master);
//        client.addTransportAddress(slave1);
//        client.addTransportAddress(slave2);
        return client;
    }
}
