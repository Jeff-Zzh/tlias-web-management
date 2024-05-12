package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class DemoFilter implements Filter {
    @Override // 初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Filter1 init");
    }

    @Override // 拦截到请求之后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter1 doFilter 拦截到请求");
        System.out.println("Filter1 放行前逻辑");
        // 放行请求，让请求访问资源，不放行，请求就访问不到controller
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter1 放行后逻辑");
    }

    @Override // 销毁方法，只调用一次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Filter1 destroy");
    }
}
