package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.vod.service.FileService;
import com.atguigu.ggkt.vod.utils.ConstantPropertiesUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Upload;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author Qiao
 * @Create 2022/9/23 09:26
 */

@Service
public class FileServiceImpl implements FileService {

    //文件上传
    @Override
    public String upload(MultipartFile file) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        Region region = new Region(ConstantPropertiesUtil.END_POINT);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);


        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        // 对象键(Key)是对象在存储桶中的唯一标识。
        //在文件名称前面添加uuid值
        String key = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();

        //对上传文件分组，根据日期  //2022/09/23
        String dateTime = new DateTime().toString("yyyy/MM/dd");
        key = dateTime+"/"+key;

        try {
            //获取上传文件的输入流
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);

            // 高级接口会返回一个异步结果Upload
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            //返回上传文件路径
            //https://ggkt-1312158966.cos.ap-beijing.myqcloud.com/Snipaste_2022-06-29_15-03-33.png
            String url = "https://"+bucketName+"."+"cos"+"."+ConstantPropertiesUtil.END_POINT+".myqcloud.com"+"/"+key;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
