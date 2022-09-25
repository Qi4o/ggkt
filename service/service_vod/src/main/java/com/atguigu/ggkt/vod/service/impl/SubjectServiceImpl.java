package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.listener.SubjectListener;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-24
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectListener subjectListener;


    //课程分类列表
    //懒加载，每次查询一层数据
    @Override
    public List<Subject> selectSubjectList(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);

        //subjectList需要遍历，判断是否有下一层数据
//        for (int i = 0; i < subjectList.size(); i++) {
//            QueryWrapper<Subject> tempWrapper = new QueryWrapper<>();
//            tempWrapper.eq("parent_id", subjectList.get(i).getId());
//            List<Subject> tempSubjectList = baseMapper.selectList(tempWrapper);
//            if (!tempSubjectList.isEmpty()){
//                subjectList.get(i).setHasChildren(true);
//            }
//        }

        //改良
        for(Subject subject : subjectList){
            Long subjectId = subject.getId();
            QueryWrapper<Subject> tempWrapper = new QueryWrapper<>();
            tempWrapper.eq("parent_id", subjectId);
            Integer count = baseMapper.selectCount(tempWrapper);
            subject.setHasChildren(count > 0);
        }

        return subjectList;
    }

    //课程分类导出
    @Override
    public void exportData(HttpServletResponse response) {
        try{
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //这里URLEncoder.encode可以防止中文乱码，和easyExcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //查询课程分类表所有数据
            List<Subject> subjectList = baseMapper.selectList(null);


            List<SubjectEeVo> subjectEeVoList = new ArrayList<>();
            //List<Subject> --- List<SubjectEeVo>
            for(Subject subject : subjectList){
                SubjectEeVo subjectEeVo = new SubjectEeVo();
//                subjectEeVo.setId(subject.getId());
//                subjectEeVo.setParentId(subject.getParentId());
                BeanUtils.copyProperties(subject, subjectEeVo);
                subjectEeVoList.add(subjectEeVo);
            }

            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEeVoList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //课程分类导入
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectEeVo.class, subjectListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
