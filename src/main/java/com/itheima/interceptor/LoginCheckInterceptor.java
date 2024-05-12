package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标资源方法（controller中的方法）运行前运行，返回true：放行，返回false：不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");

        // 获取请求url
        String url = request.getRequestURI();
        log.info("请求url：{}", url);
        // 判断是否是登录(判断url中是否有login)， 如果是登录操作，直接放行
        if (url.contains("login")) {
            log.info("登录操作，放行。。。");
            return true;
        }
        // 不是登录操作，则获取请求头中的令牌token
        String jwt_token = request.getHeader("token");
        // 令牌是否存在？不存在，则说明没有令牌，没有登录，不放行
        if (!StringUtils.hasLength(jwt_token)) {//jwt_token 为 null 或 ""，即字符串没有长度
            log.info("请求头token为空，返回未登录信息");
            Result notLogin = Result.error("NOT_LOGIN");
            // 手动转化对象--> json，以返回给前端
            String jsonString = JSONObject.toJSONString(notLogin);
            response.getWriter().write(jsonString); // 将json string响应给浏览器
            return false; // 不放行
        }
        // 令牌存在，但令牌校验不通过，令牌校验不通过，就不能放行
        try {
            JwtUtils.parseJwt(jwt_token);
        } catch (Exception e) { // 报错-> jwt令牌解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result notLogin = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(notLogin);
            response.getWriter().write(jsonString);
            return false;
        }
        // 令牌存在 且 令牌校验通过-->放行
        log.info("令牌合法，放行");
        return true;//放行，这之后才能去访问controller中的方法
    }

    @Override// 目标资源方法（controller中的方法）运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("afterCompletion...");
    }
}
