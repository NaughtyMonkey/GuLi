package com.feng.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.commonutils.ResultEntity;
import com.feng.eduservice.entity.EduTeacher;
import com.feng.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author MrFeng
 * @since 2021-01-24
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController
{

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 访问地址： http://localhost:8001/eduservice/teacher/findAll
     * rest风格,/findAll  /加不加都可以
     *
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public ResultEntity findAllTeacher()
    {
        List<EduTeacher> list = teacherService.list(null);
        return ResultEntity.ok().data("items", list);
    }

    /**
     * 逻辑删除
     *
     * @param id 要删除的id
     * @return
     */
    @ApiOperation(value = "根据ID逻辑删除讲师")
//    @ApiImplicitParam(name="id",value = "讲师Id",dataType = "String")
    @DeleteMapping("{id}")
    public ResultEntity removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id)
    {
        String str = id;
        boolean flag = teacherService.removeById(id);
        return ResultEntity.ok();
    }

    /**
     * 讲师分页查询
     * @param currentPage 当前页
     * @param limit 每页记录数
     * @return 数据集合
     */
    @GetMapping("pageTeacher/{currentPage}/{limit}")
    public ResultEntity pageListTeacher(
           @ApiParam(name="currentPage",value = "当前页",required = true)
           @PathVariable Long currentPage,
           @ApiParam(name="limit",value = "每页记录数",required = true)
           @PathVariable Long limit
    )
    {
        //创建page对象
        Page<EduTeacher> pageParam=new Page<>(currentPage,limit);
        //调用方法时候，底层封装，把分页所有数据封装到pageParam中
        IPage<EduTeacher> teacherIPage = teacherService.page(pageParam, null);

        teacherService.page(pageParam,null);
        long total = pageParam.getTotal();//总记录数
        List<EduTeacher> teacherList = pageParam.getRecords();//数据集合
        return ResultEntity.ok().data("total",total).data("rows",teacherList);
    }
}

