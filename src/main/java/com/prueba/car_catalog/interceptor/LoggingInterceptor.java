package com.prueba.car_catalog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().contains("/api/cars/all") && "GET".equalsIgnoreCase(request.getMethod())) {
            logger.info("""
                    Call to the endpoint /api/cars/all
                    IP: {}
                    Parameters: {}"
                    """,
                    request.getRemoteAddr(),
                    request.getQueryString());
        }
        return true; // Continue with execution
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (request.getRequestURI().contains("/api/cars/all") && "GET".equalsIgnoreCase(request.getMethod())) {
            logger.info("""
                    Answer sent:
                    Status: {}
                    Size: {} bytes
                    URL: {}
                    """,
                    response.getStatus(),
                    response.getBufferSize(),
                    request.getRequestURI());
        }
    }
}
