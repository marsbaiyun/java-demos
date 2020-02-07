package cn.mars.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Description：
 * Created by Mars on 2019/12/29.
 */
@RestControllerAdvice
public class GlobalRestControllerAdvice {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    public String jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("URL: {}, ERROR: {}", req.getRequestURI(), e.getMessage());
        return "busy";
    }
}
