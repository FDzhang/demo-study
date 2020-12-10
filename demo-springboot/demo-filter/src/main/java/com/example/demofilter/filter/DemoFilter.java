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
// * 在doFilter（）方法中，chain.doFilter()前的一般是对request执行的过滤操作，chain.doFilter()后面的代码一般是对response执行的操作
// *
// * @author ：zxq
// * @date ：Created in 2020/4/27 17:54
// */
//
//@Order(1)
//@WebFilter(filterName = "DemoFilter", urlPatterns = "/*", initParams = {
//        @WebInitParam(name = "URL", value = "http://localhost:8082"),
//        @WebInitParam(name = "cityCode", value = "001")})
//@Slf4j
//public class DemoFilter implements Filter {
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
//        log.info("demoFilter");
//
//        // 获取请求参数中的cityCode
//        String cityCode = request.getParameter("cityCode");
//        log.info("filter {}", cityCode);
//        // 如果 cityCode==001 则通过
//        if ("001".equals(cityCode)) {
//            // 放行
//            log.info("放行");
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            // 返回错误信息
//            log.info("返回错误信息");
//            Map<String, Object> map = new HashMap<>(4);
//            map.put("code", "301");
//            map.put("message", "cityCode错误");
//
//            // 设置编码 和 数据格式
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//
//            Gson gson = new Gson();
//            response.getWriter().write(gson.toJson(map));
//        }
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
