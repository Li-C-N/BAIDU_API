package com.ljd.ai.controller;

import com.ljd.ai.aip.Selfie_anime;
import com.ljd.ai.enums.CustomizeCode;
import com.ljd.ai.mapper.ImageInfoMapper;
import com.ljd.ai.service.ImageInfoService;
import com.ljd.ai.service.UploadAndConvertService;
import com.ljd.ai.utils.Result;
import org.springframework.stereotype.Controller;


import com.ljd.ai.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @class: UpLoad
 * @author: ljd
 * @create: 2021/3/26 15:15
 **/
@Controller
public class UpLoadAndConvertController {
    @Autowired
    private UploadAndConvertService uploadService;
    @Autowired
    private ImageInfoService imageInfoService;
    @GetMapping("/uploadImg")
    public String upload()
          {

        return "index";
    }

    @PostMapping("/uploadImg")
    @ResponseBody
    public Result<?> upload(HttpServletRequest httpServletRequest,
                            @RequestParam("file") MultipartFile file)
            throws IOException {
        String imgBefore = uploadService.uploadBeforeToOss(file);
        String imgAfter = uploadService.uploadAfterToOss(new Selfie_anime().selfie_anime(imgBefore));
        Map<String, Object> map = new HashMap<>();
        map.put("imgBefore", imgBefore);
        map.put("imgAfter", imgAfter);
        if (imageInfoService.insert(imgBefore, imgAfter)) {
            return Result.okOf(CustomizeCode.UPLOAD_IMG_SUCCESS, map);
        }
        return Result.okOf(CustomizeCode.UPLOAD_IMG_FAILED, "ljd");
    }


}
