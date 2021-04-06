package com.ljd.ai.service;
import com.fasterxml.jackson.core.SerializableString;
import com.ljd.ai.aip.Selfie_anime;
import com.ljd.ai.mapper.ImageInfoMapper;
import com.ljd.ai.model.ImageInfoExample;
import com.ljd.ai.utils.AliyunOSSUtil;
import com.ljd.ai.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * @class: UploadService
 * @author: ljd
 * @create: 2021/3/31 13:13
 **/
@Service
public class UploadAndConvertService {
    @Autowired
    private ImageInfoMapper imageInfoMapper;
    public String uploadBeforeToOss(MultipartFile file) throws IOException {
        AliyunOSSUtil aliyunOSSUtil = new AliyunOSSUtil();
        return aliyunOSSUtil.upload(file, 0);
    }

    //    public  boolean Convert(String Url) {
//        AliyunOSSUtil aliyunOSSUtil =new AliyunOSSUtil();
//
//        return ;
//    }
    public String uploadAfterToOss(MultipartFile file) throws IOException {
        AliyunOSSUtil aliyunOSSUtil = new AliyunOSSUtil();
        return aliyunOSSUtil.upload(file, 1);
    }

    public long convertCount() throws SQLException {
        ImageInfoExample  example = new ImageInfoExample();
        ImageInfoExample.Criteria criteria = example.createCriteria();
        return imageInfoMapper.countByExample(example);
    }


}