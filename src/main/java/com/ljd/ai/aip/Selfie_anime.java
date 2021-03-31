package com.ljd.ai.aip;

import com.alibaba.fastjson.JSONObject;
import com.ljd.ai.controller.UpLoadAndConvert;
import com.ljd.ai.service.UploadAndConvertService;
import com.ljd.ai.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 人像动漫化
 */
public class Selfie_anime {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    @Autowired
    private UploadAndConvertService uploadAndConvertService;
    public MultipartFile selfie_anime(String Url) {
        String  imageStr="";
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-process/v1/selfie_anime";
        try {
            // 本地文件路径
            URL httpUrl = new URL(Url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            //通过输入流获取图片数据

//            String filePath = ;
//            byte[] imgData = FileUtil.readFileByBytes(inStream);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len = 0;
            while( (len=inStream.read(buffer)) != -1 ){
                outStream.write(buffer, 0, len);
            }
            inStream.close();
            byte[] imgData =  outStream.toByteArray();
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = JSONObject.parseObject(result);
             imageStr = jsonObject.getString("image");

//            BaseConvert.GenerateImage(imageStr, "C:\\Users\\Administrator\\Desktop\\"+number+".png");
//            System.out.println(result.);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"".equals(imageStr))
        {
            System.out.println("//////////////////////////////////////////////////////");
        }

        return BASE64DecodedMultipartFile.base64ToMultipart(imageStr);
    }

//    public static void main(String[] args) {
//        Selfie_anime.selfie_anime();
//    }
}