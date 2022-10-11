package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.VodService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author Qiao
 * @Create 2022/10/10 16:28
 */

@Api("腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频的接口
    @PostMapping("upload")
    public Result upload(){
        String filedId = vodService.updateVideo();
        return Result.ok(filedId);
    }

    //删除腾讯云中的视频
    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId){
        vodService.removeVideo(fileId);
        return Result.ok(null);
    }
}
