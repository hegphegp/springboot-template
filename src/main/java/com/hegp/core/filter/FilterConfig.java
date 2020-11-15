package com.hegp.core.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @author hgp
 * @date 20-5-27
 */
@Configuration
public class FilterConfig {
    @Value("${url.authentication.white-list}")
    private Set<String> whiteList;

    @Value("${rate-limiter.one-second.limit:2000}")
    private Double oneSecondRateLimiter = 2000d;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*"); //拦截的URL
        registration.setName("corsFilter");
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<RateLimiterFilter> rateLimiterFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RateLimiterFilter(oneSecondRateLimiter));
        registration.addUrlPatterns("/*"); //拦截的URL
        registration.setName("rateLimiterFilter");
        registration.setOrder(Integer.MIN_VALUE+1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter(redisTemplate, whiteList));
        registration.addUrlPatterns("/*"); //拦截的URL
        registration.setName("authenticationFilter");
        registration.setOrder(Integer.MIN_VALUE+1);
        return registration;
    }

}