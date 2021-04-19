package com.feng.eduservice.service;

import com.feng.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author MrFeng
 * @since 2021-04-03
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 保存课程信息
     * @param courseInfoVo
     * @return
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
