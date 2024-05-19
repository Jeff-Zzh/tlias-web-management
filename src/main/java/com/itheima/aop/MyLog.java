package com.itheima.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 注解作用在哪些地方？ 方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时注解生效
public @interface MyLog {
}
