package com.feng.eduservice.controller;


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
@Api(description="讲师管理")
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
    public List<EduTeacher> findAllTeacher()
    {
        return teacherService.list(null);
    }

    /**
     * 逻辑删除
     * @param id 要删除的id
     * @return
     */
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @ApiImplicitParam(name = "id", value = "讲师ID", required = true)
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id)
    {
        return teacherService.removeById(id);
    }
}
