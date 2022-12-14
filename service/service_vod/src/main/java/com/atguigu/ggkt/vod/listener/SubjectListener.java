package com.atguigu.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Qiao
 * @Create 2022/9/24 15:47
 */

@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    //注入mapper
//    @Autowired
//    private SubjectMapper subjectMapper;

    @Autowired
    private SubjectService subjectService;


    //一行行，从第二行
    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();

        BeanUtils.copyProperties(subjectEeVo, subject);

        //添加
//        subjectMapper.insert(subject);
        subjectService.save(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
