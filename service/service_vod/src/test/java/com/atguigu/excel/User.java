package com.atguigu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author Qiao
 * @Create 2022/9/24 14:09
 */

@Data
public class User {
    @ExcelProperty(value = "用户编号", index = 0)
    private int id;

    @ExcelProperty(value = "用户名称", index = 1)
    private String name;
}
