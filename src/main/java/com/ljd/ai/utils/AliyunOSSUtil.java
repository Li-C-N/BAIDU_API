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

    private final String endpoint="https://oss-cn-beijing.aliyuncs.com";
    private final String accessId="LTAI5tSQhcWshCoNQrwWBXi8";
    private final String accessKey="YPQY8U9XwA8dHlWRZVSBOOE3X7B5KJ";
    private final String bucketName="ljd-website";
    private final String webUrl="https://ljd-website.oss-cn-beijing.aliyuncs.com";
    private final String folderBefore="Before/";
    private final String folderAfter="After/";
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AliyunOSSUtil.class);
    public boolean upload (MultipartFile file) throws URISyntaxException {
        String imgUrl="";
        try {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            String newFileName = sdf.format(new Date()) + r.nextInt(100) + suffixName;
             imgUrl = this.ossClient(newFileName,file);
            System.out.println("图片地址返回"+imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        ImageInfo img = new ImageInfo();
        img.setiImageConvertAfterUrl(imgUrl);
        return imageInfoMapper.insertSelective(img)==1;
    }


    /** 上传文件*/
    private String ossClient(String newFileName, MultipartFile file) {
        String imgUrl = "";
        // 创建OSSClient实例
        OSS client = new OSSClientBuilder().build(endpoint,accessId ,accessKey );
        try {
            PutObjectResult result =client.putObject(bucketName,folderBefore+newFileName, new ByteArrayInputStream(file.getBytes()));
            client.shutdown();
            if (null != result) {
                // 获取上传后的图片链接
                // 后端将地址拼接一下，oss那里设为了公共读，阿里云oss屁事太多了
                imgUrl= webUrl + "/" + folderBefore+ newFileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            imgUrl = "";
        }
        return imgUrl;
    }
    }

