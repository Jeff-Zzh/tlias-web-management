package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求url
        String url = request.getRequestURI();
        log.info("请求url：{}",url);
        // 判断是否是登录(判断url中是否有login)， 如果是登录操作，直接放行
        if(url.contains("login")){
            log.info("登录操作，放行。。。");
            filterChain.doFilter(request, response);
            return;
        }
        // 不是登录操作，则获取请求头中的令牌token
        String jwt_token = request.getHeader("token");
        // 令牌是否存在？不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt_token)){//jwt_token 为 null 或 ""，即字符串没有长度
            log.info("请求头token为空，返回未登录信息");
            Result notLogin = Result.error("NOT_LOGIN");
            // 手动转化对象--> json，以返回给前端
            String jsonString = JSONObject.toJSONString(notLogin);
            response.getWriter().write(jsonString); // 将json string响应给浏览器
            return;
        }
        // 令牌存在，但令牌校验不通过，返回错误结果（未登录）
        try {
            JwtUtils.parseJwt(jwt_token);
        } catch (Exception e) { // 报错-> jwt令牌解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result notLogin = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(notLogin);
            response.getWriter().write(jsonString);
            return;
        }
        // 令牌存在 且 令牌校验通过-->放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
