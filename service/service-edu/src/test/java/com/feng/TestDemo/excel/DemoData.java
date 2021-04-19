package com.feng.TestDemo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData
{
    //设置Excel表头
    @ExcelProperty(value = "学员编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学员名字",index = 1)
    private String sname;
}
