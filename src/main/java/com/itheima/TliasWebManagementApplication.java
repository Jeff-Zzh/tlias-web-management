package com.itheima;

import org.dom4j.io.SAXReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan // 开启对servlet组件的支持
//@ComponentScan({"com.itheima","com.example"}) 指定要扫描的包
@SpringBootApplication
public class TliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

//    // 声明第三方bean
//    @Bean // 将当前方法的返回值对象交给IOC容器管理，成为IOC容器的Bean
//    public SAXReader saxReader(){
//        return new SAXReader();
//    }
}
