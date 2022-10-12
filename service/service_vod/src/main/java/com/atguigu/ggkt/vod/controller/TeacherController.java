package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-09-20
 */

@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/admin/vod/teacher")
//@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //http://localhost:8301/admin/vod/teacher/findAll
    //1.查询所有讲师
//    @ApiOperation("查询所有讲师")
//    @GetMapping("findAll")
//    public List<Teacher> findAllTeacher(){
//        return teacherService.list();
//    }
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result findAllTeacher(){
        return Result.ok(teacherService.list());
    }

    //2.逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
                                 @PathVariable("id") Long id){
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess){
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //3.分页条件查询讲师
    @ApiOperation(("分页条件查询讲师"))
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable("current") Long current,
                           @PathVariable("limit") Long limit,
                           @RequestBody TeacherQueryVo teacherQueryVo){

        //创建Page对象
        Page<Teacher> pageParam = new Page<>(current, limit);

        //判断teacherQueryVo对象是否为空
        if (teacherQueryVo == null){    //查询全部
            System.out.println("---------------查询全部-------------------");
            return Result.ok(teacherService.page(pageParam, null));
        } else {
            System.out.println("---------------条件查询-------------------");
            System.out.println(teacherQueryVo);
            //获取条件值，进行非空判断，条件封装
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            //进行非空判断，条件封装
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(name)){
                wrapper.like("name", name);
            }
            if (!StringUtils.isEmpty(level)){
                wrapper.like("level", level);
            }
            if (!StringUtils.isEmpty(joinDateBegin)){
                wrapper.like("join_date", joinDateBegin);
            }
            if (!StringUtils.isEmpty(joinDateEnd)){
                wrapper.like("join_date", joinDateEnd);
            }

            //调用方法
            Page<Teacher> pageModel = teacherService.page(pageParam, wrapper);

            //返回
            return Result.ok(pageModel);
        }
    }

    //4.添加讲师
    @ApiOperation("添加讲师")
    @PostMapping("/saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher){
        boolean isSuccess = teacherService.save(teacher);

        if (isSuccess){
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

//    //5.修改讲师
//    @ApiOperation("修改讲师")
//    @PostMapping("modifyTeacher")
//    public Result modifyTeacher(@RequestBody Teacher teacher){
//
//        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", teacher.getId());
//        boolean isSuccess = teacherService.update(teacher, wrapper);
//
//        if (isSuccess){
//            return Result.ok(null);
//        } else {
//            return Result.fail(null);
//        }
//    }

    //5.根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable("id") Long id){
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    //6.修改讲师
    @ApiOperation("修改讲师")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess){
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //7.批量删除老师
    @ApiOperation("批量删除老师")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList){
        System.out.println(idList);
        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess){
            System.out.println("成功");
            return Result.ok(null);
        } else {
            System.out.println("失败");
            return Result.fail(null);
        }
    }



}

