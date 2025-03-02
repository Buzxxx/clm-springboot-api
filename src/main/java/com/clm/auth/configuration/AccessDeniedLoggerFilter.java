package com.clm.auth.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedLoggerFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedLoggerFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        chain.doFilter(request, response);

        if (httpResponse.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
            logger.warn("403 Forbidden - User unauthorized: Method: {}, Path: {}, IP: {}",
                    httpRequest.getMethod(), httpRequest.getRequestURI(), httpRequest.getRemoteAddr());
        }
    }
}
