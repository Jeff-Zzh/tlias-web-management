package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.service.AliOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;
    @Autowired
    private AliOSSProperties aliOSSProperties;

    private EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();


    public AliOSSUtils() throws ClientException {
    }

    public String uploadFile(MultipartFile file) throws ClientException, IOException {
        String endpoint = aliOSSProperties.getEndpoint();
        String bucketName = aliOSSProperties.getBucketName();
        // 获取上传的文件输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖， UUID构建新文件名
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 上传文件到OSS服务
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        // 上传文件请求
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        String url = endpoint.split("//")[0] + "//" + bucketName + "." +endpoint.split("//")[1]+ "/" + fileName;

        // 关闭OSSClient
        ossClient.shutdown();
        return url;
    }
}
