package cn.mars.delayqueue.entity;

import java.io.Serializable;

/**
 * Description：
 * Created by Mars on 2018/4/12.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 8631034521494779887L;

    private String topic;
    private String id;
    private int delay;
    private int ttr;
    private String body;

    public Message() {
    }

    public Message(String topic, String id, int delay, int ttr) {
        this.topic = topic;
        this.id = id;
        this.delay = delay;
        this.ttr = ttr;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getTtr() {
        return ttr;
    }

    public void setTtr(int ttr) {
        this.ttr = ttr;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topic='" + topic + '\'' +
                ", id='" + id + '\'' +
                ", delay=" + delay +
                ", ttr=" + ttr +
                ", body='" + body + '\'' +
                '}';
    }
}
