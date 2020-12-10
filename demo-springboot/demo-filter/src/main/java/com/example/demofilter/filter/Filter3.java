//package com.example.demofilter.filter;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * order 越小 越先被经过
// * 即：通过filter的顺序：根据order从小到大
// *
// * @author ：zxq
// * @date ：Created in 2020/4/27 17:54
// */
//
//@Order(3)
//@WebFilter(filterName = "DemoFilter", urlPatterns = "/*", initParams = {
//        @WebInitParam(name = "URL", value = "http://localhost:8082"),
//        @WebInitParam(name = "cityCode", value = "001")})
//@Slf4j
//public class Filter3 implements Filter {
//
//    private String url;
//
//    /**
//     * 可以初始化Filter在web.xml里面配置的初始化参数
//     * filter对象只会创建一次，init方法也只会执行一次。
//     */
//    @Override
//    public void init(FilterConfig filterConfig) {
//        System.out.println("---过滤器的初始化方法！---");
//        this.url = filterConfig.getInitParameter("URL");
//
//        System.out.println("URL=" + this.url);
//        System.out.println("cityCode=" + filterConfig.getInitParameter("cityCode"));
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
//        log.info("filterChain.doFilter 之前");
//
//        filterChain.doFilter(servletRequest, servletResponse);
//
//        log.info("filterChain.doFilter 之后");
//    }
//
//    /**
//     * 在销毁Filter时自动调用。
//     */
//    @Override
//    public void destroy() {
//        System.out.println("---销毁Filter---");
//    }
//}
