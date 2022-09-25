package com.atguigu.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-24
 */
public interface SubjectService extends IService<Subject> {

    //课程分类列表
    List<Subject> selectSubjectList(Long id);

    //课程导出
    void exportData(HttpServletResponse response);

    //课程导入
    void importData(MultipartFile file);
}
