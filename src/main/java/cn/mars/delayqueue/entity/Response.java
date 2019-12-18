package cn.mars.delayqueue.entity;

import java.io.Serializable;

/**
 * Description：
 * Created by Mars on 2018/4/12.
 */
public class Response implements Serializable {

    private static final long serialVersionUID = -152290263925788883L;

    private int code;
    private String description;
    private Message data;

    public Response() {
    }

    public Response(int code, String description, Message data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Message getData() {
        return data;
    }

    public void setData(Message data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
