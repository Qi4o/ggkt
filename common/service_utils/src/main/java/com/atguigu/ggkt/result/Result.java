package com.atguigu.ggkt.result;

import lombok.Data;

import java.util.List;

/**
 * @Author Qiao
 * @Create 2022/9/20 19:20
 */

//统一返回结果类
@Data
public class Result<T> {

    private Integer code;       //状态码
    private String  message;    //返回状态信息
    private T       data;       //返回数据

    public Result(){}

//    //成功的方法，没有data数据
//    public static<T> Result<T> ok(){
//        Result<T> result = new Result<>();
//        result.setCode(200);
//        result.setMessage("成功");
//        return result;
//    }
//
//    //失败的方法，没有data数据
//    public static<T> Result<T> fail(){
//        Result<T> result = new Result<>();
//        result.setCode(201);
//        result.setMessage("失败");
//        return result;
//    }


    //成功的方法，有data数据
    public static<T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        result.setCode(20000);
        result.setMessage("成功");
        return result;
    }

    //失败的方法，有data数据
    public static<T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(20001);
        result.setMessage("失败");
        return result;
    }

    public Result message(String message){
        this.message = message;
        return this;
    }

    public Result code(Integer code){
        this.code = code;
        return this;
    }

}
