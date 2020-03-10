package cn.mars.constants;

/**
 * Description：
 * Created by Mars on 2020/2/29.
 */
public enum ErrorCodeEnum {

    SYS_ERROR(2001001000, "服务端发生异常"),

            ;

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
