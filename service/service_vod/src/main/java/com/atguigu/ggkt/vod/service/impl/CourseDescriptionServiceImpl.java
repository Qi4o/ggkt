package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vod.mapper.CourseDescriptionMapper;
import com.atguigu.ggkt.vod.mapper.TeacherMapper;
import com.atguigu.ggkt.vod.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author Qiao
 * @Create 2022/9/25 09:29
 */

@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {
}
