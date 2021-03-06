//package com.example.demofilter.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * @author ：zxq
// * @date ：Created in 2020/12/10 16:44
// */
//
//
//@Order(2)
//@WebFilter(filterName = "Filter5", urlPatterns = "/*")
//@Slf4j
//public class Filter5 implements Filter {
//
//    private String url;
//
//    /**
//     * 可以初始化Filter在web.xml里面配置的初始化参数
//     * filter对象只会创建一次，init方法也只会执行一次。
//     */
//    @Override
//    public void init(FilterConfig filterConfig) {
//        log.info("---过滤器的初始化方法！---");
//    }
//
//    /**
//     * 主要的业务代码编写方法
//     */
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String contextPath = request.getContextPath();
//        String requestURI = request.getRequestURI();
//        StringBuffer requestURL = request.getRequestURL();
//        String method = request.getMethod();
//        String queryString = request.getQueryString();
//        String servletPath = request.getServletPath();
//        HttpSession session = request.getSession();
//
//        if ("/filter/test".equals(servletPath)){
//            request.getRequestDispatcher("/page").forward(request,response);
//        }
//
//        log.info("doFilter 之前");
////        filterChain.doFilter(servletRequest, servletResponse);
//        log.info("doFilter 之后");
//
//    }
//
//    /**
//     * 在销毁Filter时自动调用。
//     */
//    @Override
//    public void destroy() {
//        log.info("---销毁Filter---");
//    }
//}
