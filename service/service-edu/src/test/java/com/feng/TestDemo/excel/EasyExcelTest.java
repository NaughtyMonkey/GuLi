package com.feng.TestDemo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest
{
    public static void main(String[] args)
    {
        //1、Excel写操作
//        String fileName = "D:\\exceldata\\Student.xlsx";
        //1.1、调用easyexcel里面的方法实现写操作，write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
//        EasyExcel.write(fileName, DemoData.class).sheet("学员列表").doWrite(getData());

        //2.Excel写操作
        String fileName = "D:\\exceldata\\Student.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    private static List<DemoData> getData()
    {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
