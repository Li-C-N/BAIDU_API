package com.ljd.ai.service;

import com.ljd.ai.mapper.ImageInfoMapper;
import com.ljd.ai.model.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * @class: ImageInfoService
 * @author: ljd
 * @create: 2021/3/31 15:15
 **/


public class ImageInfoService {
    @Autowired
    private ImageInfoMapper imageInfoMapper;

    public boolean insert (String imgBefore,String imgAfter){
        ImageInfo record =new ImageInfo();
        record.setiImageConvertAfterUrl(imgAfter);
        record.setiImageConvertBeforeUrl(imgBefore);
        return imageInfoMapper.insert(record)==1;
    }
}
