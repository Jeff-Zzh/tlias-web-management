package com.itheima.config;

import com.itheima.service.DeptService;
import com.itheima.service.impl.DeptServiceImpl;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 配置类，一般第三方bean都放到配置类中
public class CommonConfig {
    // 声明第三方bean
    @Bean // 将当前方法的返回值对象交给IOC容器管理，成为IOC容器的Bean
    // 通过@Bean注解的name/value属性指定bean名称，如果未指定，bean名默认是方法名
    public SAXReader saxReader(DeptService deptService){ // 声明第三方bean时需要依赖其他bean对象，直接在bean定义方法中设置形参即可，容器会根据类型自动装配
        return new SAXReader();
    }
}
