package com.example.demofilter.filters;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 日志打印
 * @author ：zxq
 * @date ：Created in 2021/2/19 15:15
 */
@Slf4j
@Order(3)
@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化Log过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString();

        log.info("请求的接口为: {} {}", method, requestUri);
        log.info("queryString : {}", queryString);

        if ("POST".equals(method)) {
            String body = IoUtil.readUtf8(request.getInputStream());
            log.info("body: {}", body);
            //log.info("body: {}", JSON.parseObject(body).toJSONString()); //格式化
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
