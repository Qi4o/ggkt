package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author Qiao
 * @Create 2022/9/24 14:23
 */

public class TestRead {

    public static void main(String[] args) {
        String fileName = "/Users/qiao/Downloads/atguigu.xlsx";
        //调用方法
        EasyExcel.read(fileName, User.class, new ExcelListener()).sheet().doRead();
    }
}
