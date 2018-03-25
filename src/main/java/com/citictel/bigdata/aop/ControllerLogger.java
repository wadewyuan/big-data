package com.citictel.bigdata.aop;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Aspect
@Component
public class ControllerLogger {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogger.class);

    @After("execution(public * com.citictel.bigdata.controller.*Controller.*(..))")
    private void loggControllerRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if(request.getUserPrincipal() != null) {
            String clientId = request.getUserPrincipal().getName();
            String requestMethod = request.getMethod();
            String requestUri = request.getRequestURI();
            String body = new String(((ContentCachingRequestWrapper) request).getContentAsByteArray());
            String queryString = request.getQueryString();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CLIENT [" + clientId + "] REQUEST DATA: " + requestUri + ", METHOD: " + requestMethod);
            if(!StringUtils.isEmpty(queryString)) {
                stringBuilder.append(", QUERY STRING: " + queryString);
            }
            if (!StringUtils.isEmpty(body)) {
                stringBuilder.append(", PAYLOAD: \n" + body);
            }

            logger.debug(stringBuilder.toString());
        }
    }
}
