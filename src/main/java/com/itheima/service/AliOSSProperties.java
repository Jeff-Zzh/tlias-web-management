package com.itheima.service;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss") // 批量进行外部属性注入
public class AliOSSProperties {
    private String endpoint;
    private String bucketName;
}
