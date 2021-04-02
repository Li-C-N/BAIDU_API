package com.ljd.ai.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ljd.ai.dto.FileDTO;
import com.ljd.ai.mapper.ImageInfoMapper;
import com.ljd.ai.dto.FileDTO;
import com.ljd.ai.enums.CustomizeCode;
import com.ljd.ai.mapper.ImageInfoMapper;
import com.ljd.ai.model.ImageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


@Component
public class AliyunOSSUtil {

    private final String endpoint = "";
    private final String accessId = "";
    private final String accessKey = "";
    private final String bucketName = "";
    private final String webUrl = "";
    private final String folderBefore = "";
    private final String folderAfter = "";
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AliyunOSSUtil.class);

    public String upload(MultipartFile file, Integer flag) throws IOException {

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String newFileName = sdf.format(new Date()) + r.nextInt(100) + suffixName;
        if (flag == 0) {

            return this.ossClient(newFileName, file, 0);
        } else {
            return this.ossClient1(newFileName, file, 1);
        }
    }


    /**
     * 上传文件
     */
    private String ossClient(String newFileName, MultipartFile file, Integer flag) throws IOException {
        // 创建OSSClient实例
        OSS client = new OSSClientBuilder().build(endpoint, accessId, accessKey);

        PutObjectResult result = client.putObject(bucketName, folderBefore + newFileName, new ByteArrayInputStream(file.getBytes()));
        client.shutdown();
        // 获取上传后的图片链接
        // 后端将地址拼接一下，oss那里设为了公共读，阿里云oss屁事太多了
            return webUrl + "/" + folderBefore + newFileName;
    }
    private String ossClient1(String newFileName, MultipartFile file, Integer flag) throws IOException {
        // 创建OSSClient实例
        OSS client = new OSSClientBuilder().build(endpoint, accessId, accessKey);

        PutObjectResult result = client.putObject(bucketName, folderBefore + newFileName, new ByteArrayInputStream(file.getBytes()));
        client.shutdown();
        // 获取上传后的图片链接
        // 后端将地址拼接一下，oss那里设为了公共读，阿里云oss屁事太多了
            return webUrl + "/" + folderAfter + newFileName;
    }
}

