package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class uploadController {
    // 本地存储文件
/*    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传{}，{}，{}", username, age, image);
        // 获取原始文件名
        String originalFilename = image.getOriginalFilename();

        // 构造唯一的文件名（不能重复），timestamp是不行的，因为同一时刻若2个用户传相同名字的文件就又重复了，用uuid（通用唯一识别码）b4def0d5-3bf8-46c0-af55-c60137ef53e4
        int extension_index = originalFilename.lastIndexOf(".");
        String extension = originalFilename.substring(extension_index);
        String newFileName = UUID.randomUUID().toString() + extension;
        log.info("新文件名{}", newFileName);

        // 将文件存储在服务器磁盘目录
        image.transferTo(new File("D:\\" + newFileName));
        return Result.success();
    }*/
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile file) throws ClientException, IOException {
        log.info("文件上传，文件名:{}", file.getOriginalFilename());
        // 调用AliOSSUtils进行文件上传

        String url = aliOSSUtils.uploadFile(file);
        log.info("文件上传完成，文件访问的url为{}", url);
        return Result.success(url);
    }

}
