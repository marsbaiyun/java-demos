package cn.mars.delayqueue.config;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Description：
 * Created by Mars on 2018/4/12.
 */
public class DQueueConfig {

    private List<String> ipList;
    private int port = 6677;
    private int timeout = 5000;

    public DQueueConfig() {
    }

    public DQueueConfig(List<String> ipList, int port, int timeout) {
        this.ipList = ipList;
        this.port = port;
        this.timeout = timeout;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public static DQueueConfig getDQueueConfig(String conf) throws Exception {
        File confFile = new File(conf);
        FileInputStream in = new FileInputStream(confFile);
        Properties prop = new Properties();
        prop.load(in);
        String ipStr = prop.getProperty("server.list");
        if(StringUtils.isBlank(ipStr)) {
            throw new Exception("server.list配置不能为空");
        } else {
            List ipList = Arrays.asList(ipStr.split(","));
            int port = StringUtils.isBlank(prop.getProperty("server.port"))?6677:Integer.valueOf(prop.getProperty("server.port")).intValue();
            int timeout = StringUtils.isBlank(prop.getProperty("timeout"))?5000:Integer.valueOf(prop.getProperty("timeout")).intValue();
            return new DQueueConfig(ipList, port, timeout);
        }
    }

    public String getServerIp() {
        if(this.ipList != null && this.ipList.size() != 0) {
            if(this.ipList.size() == 1) {
                return (String)this.ipList.get(0);
            } else {
                Random random = new Random();
                return (String)this.ipList.get(random.nextInt(this.ipList.size()));
            }
        } else {
            return "";
        }
    }
}
