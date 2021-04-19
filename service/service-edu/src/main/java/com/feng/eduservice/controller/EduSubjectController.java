package com.feng.eduservice.controller;


import com.feng.commonutils.ResultEntity;
import com.feng.eduservice.entity.subject.OneSubject;
import com.feng.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *添加课程分类
 *
 * @author MrFeng
 * @since 2021-03-06
 */
@Api(description = "课程添加管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edusubject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    /**
     * 根据上传的Excel文件添加课程分类
     *
     * @param file
     * @return
     */
    @PostMapping("addSubject")
    public ResultEntity addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return ResultEntity.ok();
    }

    /**
     * 获取课程分类树形结构数据
     * @return
     */
    @GetMapping("getAllSubject")
    public ResultEntity getAllSubject(){
        //一级分类list
        List<OneSubject> list=subjectService.getAllOneTwoSubject();
        return ResultEntity.ok().data("list",list);
    }
}

