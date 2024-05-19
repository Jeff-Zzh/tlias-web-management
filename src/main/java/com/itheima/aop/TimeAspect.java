package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect // AOP类
@Order(2)
public class TimeAspect {

   // @Around("execution(* com.itheima.service.*.*(..))") // 切入点表达式
    @Around("com.itheima.aop.MyAspect.pointCut()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1.记录方法开始时间
        long begin = System.currentTimeMillis();
        // 2.执行原始方法
        Object result = joinPoint.proceed();
        // 3.记录方法结束时间，计算方法执行时间
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature()+"执行时常：{}ms", end - begin); // 获取原始方法的签名

        return result;
    }
}
