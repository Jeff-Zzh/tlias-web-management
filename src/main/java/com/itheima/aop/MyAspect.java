package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect // 切面Aspect
@Order(1) // 指定通知执行顺序，数字越小越先执行before，越后执行after
public class MyAspect {
    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))") // 抽取出-切入点表达式
    public void pointCut() {} // 切入点

    @Pointcut("execution(public void com.itheima.service.DeptService.deleteDeptById(java.lang.Integer))")
    public void pointCut2(){}

//    @Pointcut("execution(* com.itheima.service.DeptService.list()) || execution(* com.itheima.service.DeptService.deleteDeptById(java.lang.Integer))")
    @Pointcut("@annotation(com.itheima.aop.MyLog)") // 切入点匹配 方法上有 @MyLog 注解的方法
    public void pointCut3(){}

    @Before("pointCut3()")
    public void before(){
        log.info("MyAspect pointCut4 - before method");
    }


    @Before("pointCut()") // 通知Advice + 切入点
    public void before(JoinPoint joinPoint) {
        log.info("前置通知-before method invoked");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /**
         * Proceedingjoinpoint 继承了 JoinPoint 。
         * 是在JoinPoint的基础上暴露出 proceed 这个方法。proceed很重要，这个是aop代理链执行的方法。
         *   环绕通知 ProceedingJoinPoint 执行proceed方法的作用是让目标方法执行，这也是环绕通知和前置、后置通知方法的一个最大区别。
         */
        log.info("环绕通知-before around method invoked");
        Object result = proceedingJoinPoint.proceed(); // 执行原始方法

        log.info("环绕通知-after around method invoked, result:" + result);
        return result; // 原始方法执行完毕的返回值
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("后置通知after-method invoked"); // 连接点不论是否执行成功/是否抛出异常后 都执行
    }

    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("方法返回后通知-afterReturning method invoked"); // 连接点正常执行成功后才执行
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("发生异常后执行-afterThrowing method invoked"); // 连接点执行抛出异常后才执行
    }
}
