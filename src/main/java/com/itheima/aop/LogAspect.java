package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect // 切面类
public class LogAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)") // 环绕通知 + 注解切入点表达式
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 操作人id - 登录员工的id
        // 获取请求头中jwt令牌，解析令牌获取当前登录用户id信息
        String jwt_token = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt_token); // claims 中是自定义的参数，就是一个map
        Integer operateUserId = (Integer) claims.get("id");
        
        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        String className = joinPoint.getTarget().getClass().getName(); // 目标对象/目标类/目标类名
        // 操作方法名
        String methodName = joinPoint.getSignature().getName(); // 目标方法签名/方法名
        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        long beginTime = System.currentTimeMillis();
        // 调用原始目标方法运行
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        // 方法返回值，将方法返回值（返回的对象）转为string
        String returnValues = JSONObject.toJSONString(result);
        // 操作耗时
        Long costTime = endTime - beginTime;


        // 记录日志
        OperateLog operateLog = new OperateLog(null, operateUserId,operateTime,className,methodName,methodParams,returnValues,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}", operateLog);
        return result;
    }
}
