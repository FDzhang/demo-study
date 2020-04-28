package com.example.demofilter.filter;

import com.google.gson.Gson;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2020/4/27 17:54
 * order(number)  :过滤器的顺序
 * filterName : 过滤器名称
 * urlPatterns : 需要过滤的路径
 * initParams : 初始化参数，存于FilterConfig中
 */

@Order(2)
@WebFilter(filterName = "DemoFilter1", urlPatterns = "/*" , initParams = {})
public class DemoFilter1 implements Filter {

    /**
     * 可以初始化Filter在web.xml里面配置的初始化参数
     * filter对象只会创建一次，init方法也只会执行一次。
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("---过滤器的初始化方法！---");
        System.out.println("cityCode=" + filterConfig.getInitParameter("cityCode"));
    }

    /**
     * 主要的业务代码编写方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String cityCode = request.getParameter("cityCode");
        if ("001".equals(cityCode)){
            // 放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else {

            Map<String,Object> map = new HashMap<>(4);
            map.put("message","cityCode错误");

            //
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(map));
        }
    }

    /**
     * 在销毁Filter时自动调用。
     */
    @Override
    public void destroy() {
        System.out.println("---销毁Filter---" );
    }
}
