package com.citictel.bigdata.logger;

import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

@ControllerAdvice
@Component
public class ControllerLogger {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogger.class);

    @InitBinder
    private void initBinder(WebDataBinder binder, WebRequest webRequest) throws IOException {
        String clientId = webRequest.getUserPrincipal().getName();
        String requestMethod = ((ServletWebRequest) webRequest).getHttpMethod().name();
        String requestUri = ((ServletWebRequest) webRequest).getRequest().getRequestURI().toString();
        String body = new String(((ContentCachingRequestWrapper) ((ServletWebRequest) webRequest).getRequest()).getContentAsByteArray());
        String queryString = ((ServletWebRequest) webRequest).getRequest().getQueryString();

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
