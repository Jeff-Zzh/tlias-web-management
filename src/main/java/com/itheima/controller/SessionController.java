package com.itheima.controller;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {
    // 设置cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie(new Cookie("login_username", "zzh"));
        return Result.success();
    }
    // 获取cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login_username")){
                System.out.println("login_username:"+cookie.getValue());
            }
        }
        return Result.success();
    }

    // 设置session
    @GetMapping("/s1")
    public Result session1(HttpSession session){
        log.info("httpsession-s1:{}", session.hashCode());
        session.setAttribute("login_user", "loren"); // 往session中存储数据
        return Result.success();
    }
    // 获取session
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("httpsession-s2:{}", session.hashCode());
        Object loginUser = session.getAttribute("login_user"); //loren
        if(loginUser != null){
            log.info("login_user:{}", loginUser);//loren
            return Result.success(loginUser);
        }else{
            return Result.error("fail to get login_user");
        }

    }
}
