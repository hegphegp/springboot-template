package com.hegp.core.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 限流的过滤器优先排在第一位
 * 世纪无奈, @Order执行顺序可能配置会不生效, 决定用FilterRegistrationBean配置
 * @author hgp
 * @date 20-5-27
 */
public class RateLimiterFilter extends OncePerRequestFilter {

    private byte[] errorMsgBytes = "{\"code\":500,\"msg\":\"限流\"}".getBytes();

    private RateLimiter rateLimiter = null;

    private final Logger logger = LoggerFactory.getLogger(RateLimiterFilter.class);

    public RateLimiterFilter(Double oneSecondRateLimiter) {
        rateLimiter = RateLimiter.create(oneSecondRateLimiter);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 全局限流
        // 等待20毫秒
        boolean result = rateLimiter.tryAcquire(10, TimeUnit.MICROSECONDS);
        if (result==false) {
            assemblyResponse(response);
            logger.error("触发全局限流");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void assemblyResponse(HttpServletResponse response) throws IOException {
        response.setStatus(500);
        response.setContentType("application/json; charset=utf-8");
        response.getOutputStream().write(errorMsgBytes);
    }
}