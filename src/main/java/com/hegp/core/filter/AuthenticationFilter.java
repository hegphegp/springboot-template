package com.hegp.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private PathMatcher pathMatcher = new AntPathMatcher();

    // 通配符匹配的白名单URL
    private Set<String> patternWhiteList = new HashSet();
    // 完全匹配的白名单URL
    private Set<String> directWhiteList = new HashSet();

    private RedisTemplate<String,Object> redisTemplate;

    public AuthenticationFilter(RedisTemplate<String,Object> redisTemplate,Set<String> whiteList) {
        this.redisTemplate = redisTemplate;
        if (!ObjectUtils.isEmpty(whiteList)) {
            for (String url : whiteList) {
                if (pathMatcher.isPattern(url) || !url.contains("{") || !url.contains("}")) {
                    patternWhiteList.add(url);
                } else {
                    directWhiteList.add(url);
                }
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String url = request.getRequestURI();
        boolean needAuthentication = true;
        if (!ObjectUtils.isEmpty(patternWhiteList) || !ObjectUtils.isEmpty(directWhiteList)) {
            if (directWhiteList.contains(url)) {
                needAuthentication = false;
            } else if (!ObjectUtils.isEmpty(patternWhiteList)) {
                if (matchPatternExists(url)) {
                    needAuthentication = false;
                }
            }
        }
        if (needAuthentication) {
            String token = request.getHeader("token");
        }
        chain.doFilter(request, response);
    }

    // 通配符匹配
    public boolean matchPatternExists(String url) {
        for (String pattern : patternWhiteList) {
            if (pattern.equals(url) || pathMatcher.match(pattern, url)) {
                return true;
            }
        }
        return false;
    }

}
