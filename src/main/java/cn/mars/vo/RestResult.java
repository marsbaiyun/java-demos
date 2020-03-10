package cn.mars.vo;

/**
 * Description：
 * Created by Mars on 2020/2/29.
 */
public class RestResult {

    private Integer code;
    private String message;
    private Object data;

    public static RestResult error(Integer code, String message) {
        RestResult result = new RestResult();
        result.code = code;
        result.message = message;
        return result;
    }

    public static RestResult ok(Object data) {
        RestResult result = new RestResult();
        result.code = 0;
        result.data = data;
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
