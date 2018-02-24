package cn.mars.mq.rabbit.enums;

/**
 * Description：
 * Created by Mars on 2017/11/23.
 */
public class MQ {

    public enum Type {
        Producer(1, "生产者"),
        Consumer(2, "消费者");
        private int code;
        private String desc;

        Type(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
