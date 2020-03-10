package cn.mars.aspect;

import cn.mars.constants.ErrorCodeEnum;
import cn.mars.exception.ServiceException;
import cn.mars.vo.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description：
 * Created by Mars on 2020/2/29.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public RestResult serviceExceptionHandler(HttpServletRequest req, Exception e) {
        ServiceException ex = (ServiceException) e;
        return RestResult.error(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResult resultExceptionHandler(HttpServletRequest req, Exception e) {
        // TODO 异常日志
        e.printStackTrace();
        // 返回
        return RestResult.error(ErrorCodeEnum.SYS_ERROR.getCode(), ErrorCodeEnum.SYS_ERROR.getMessage());
    }
}
