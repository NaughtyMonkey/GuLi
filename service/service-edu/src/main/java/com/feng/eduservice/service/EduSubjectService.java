package com.feng.eduservice.service;

import com.feng.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *添加课程模块接口
 *
 * @author MrFeng
 * @since 2021-03-06
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程分类
     * @param file
     */
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    /**
     * 获取课程分类一级二级分类数据
     * @return
     */
    List<OneSubject> getAllOneTwoSubject();
}
