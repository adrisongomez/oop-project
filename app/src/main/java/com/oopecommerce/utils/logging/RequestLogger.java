package com.oopecommerce.utils.logging;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.servlet.http.HttpServletRequest;

@RequestScope
@Component
public class RequestLogger {
    private final Logger logger = LoggerFactory.getLogger(RequestLogger.class);
    private final String requestId;
    private final String operationName;

    @Autowired
    public RequestLogger(HttpServletRequest request) {
        this.requestId = UUID.randomUUID().toString();
        this.operationName = request.getMethod() + " " + request.getRequestURI();
        MDC.put("requestId", this.requestId);
        MDC.put("operationName", this.operationName);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public String getRequestId() {
        return requestId;
    }

    public String getOperationName() {
        return operationName;
    }
}
