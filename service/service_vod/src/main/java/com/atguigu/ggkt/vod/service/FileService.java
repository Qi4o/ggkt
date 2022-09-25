package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Qiao
 * @Create 2022/9/23 09:26
 */
public interface FileService {
    //文件上传
    String upload(MultipartFile file);
}
