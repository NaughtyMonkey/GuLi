package com.feng.eduservice.controller;


import com.feng.commonutils.ResultEntity;
import com.feng.eduservice.entity.vo.CourseInfoVo;
import com.feng.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author MrFeng
 * @since 2021-04-03
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController
{

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 添加课程
     *
     * @param courseInfoVo
     * @return
     */
    @PostMapping("addCourseInfo")
    public ResultEntity addCourseInfo(@RequestBody CourseInfoVo courseInfoVo)
    {
        //返回添加课程成功后的课程ID，添加课程大纲时需要
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return ResultEntity.ok().data("courseId", id);
    }
}

