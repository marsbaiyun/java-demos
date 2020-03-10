package cn.mars.exception;

/**
 * Description：
 * Created by Mars on 2020/2/29.
 */
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
