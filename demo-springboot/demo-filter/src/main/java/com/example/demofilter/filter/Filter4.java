package com.example.demofilter.filter;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * order 越小 越先被经过
 * 即：通过filter的顺序：根据order从小到大
 * <p>
 * 在doFilter（）方法中，chain.doFilter()前的一般是对request执行的过滤操作，chain.doFilter()后面的代码一般是对response执行的操作
 *
 * @author ：zxq
 * @date ：Created in 2020/4/27 17:54
 */

@Order(1)
@WebFilter(filterName = "Filter4", urlPatterns = "/*")
@Slf4j
public class Filter4 implements Filter {

    private String url;

    /**
     * 可以初始化Filter在web.xml里面配置的初始化参数
     * filter对象只会创建一次，init方法也只会执行一次。
     */
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("---过滤器的初始化方法！---");
    }

    /**
     * 主要的业务代码编写方法
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();

        if ("/filter/test".equals(servletPath)){
//            request.getRequestDispatcher("/page").forward(request,response);
            RequestDispatcher page = request.getRequestDispatcher("/page");
            RequestDispatcher page1 = request.getRequestDispatcher("page");
            request.getRequestDispatcher("/page").forward(request,response);
        }

        log.info("doFilter 之前");
//        filterChain.doFilter(servletRequest, servletResponse);
        log.info("doFilter 之后");

    }

    /**
     * 在销毁Filter时自动调用。
     */
    @Override
    public void destroy() {
        log.info("---销毁Filter---");
    }
}
