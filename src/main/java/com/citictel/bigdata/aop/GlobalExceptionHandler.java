package com.citictel.bigdata.aop;

import com.citictel.bigdata.constants.StatusCodeEnum;
import com.citictel.bigdata.domain.ResponseData;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("", e);
        ResponseData r = new ResponseData();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            r.setStatusCodeAndMsg(StatusCodeEnum.NOT_FOUND);
        } else if (e instanceof org.springframework.security.access.AccessDeniedException) {
            r.setStatusCodeAndMsg(StatusCodeEnum.INSUFFICIENT_PRIVILEGES);
        } else {
            r.setStatusCodeAndMsg(StatusCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return r;
    }
}
