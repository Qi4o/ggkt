package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vo.vod.VideoVo;
import com.atguigu.ggkt.vod.mapper.ChapterMapper;
import com.atguigu.ggkt.vod.service.ChapterService;
import com.atguigu.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-24
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    
    @Autowired
    private VideoService videoService;

    //1 大纲列表（章节和小节列表）
    @Override
    public List<ChapterVo> getTreeList(Long courseId) {
        List<ChapterVo> list = new ArrayList<>();
        //先查找到所有的章节
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapper);

        for(Chapter chapter : chapterList){
            ChapterVo chapterVoTemp = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVoTemp);
            list.add(chapterVoTemp);
        }

        //把章节循环附上小节
        for(ChapterVo chapterVo : list){
            List<VideoVo> videoVoList = new ArrayList<>();

            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("chapter_id", chapterVo.getId());
            List<Video> listVideo = videoService.list(videoQueryWrapper);

            for(Video video : listVideo){
                VideoVo videoVoTemp = new VideoVo();
                BeanUtils.copyProperties(video, videoVoTemp);
                videoVoList.add(videoVoTemp);
            }

            chapterVo.setChildren(videoVoList);
        }

        return list;
    }

    //根据id删除章节
    @Override
    public void removeChapterByCourseId(Long id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        baseMapper.delete(wrapper);
    }
}
