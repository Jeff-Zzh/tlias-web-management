package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Filter2 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter2 doFilter 拦截到请求");
        System.out.println("Filter2 放行前逻辑");
        filterChain.doFilter(servletRequest, servletResponse);// 放行

        System.out.println("filter2 放行后逻辑");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Filter2 destroy");
    }
}
