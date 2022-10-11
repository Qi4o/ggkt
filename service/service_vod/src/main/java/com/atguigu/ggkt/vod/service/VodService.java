package com.atguigu.ggkt.vod.service;

/**
 * @Author Qiao
 * @Create 2022/10/10 16:29
 */
public interface VodService {
    //上传视频的接口
    String updateVideo();

    //删除腾讯云中的视频
    void removeVideo(String fileId);
}
