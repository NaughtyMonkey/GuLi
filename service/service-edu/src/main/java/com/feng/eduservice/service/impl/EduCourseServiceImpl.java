package com.feng.eduservice.service.impl;

import com.feng.eduservice.entity.EduCourse;
import com.feng.eduservice.entity.EduCourseDescription;
import com.feng.eduservice.entity.vo.CourseInfoVo;
import com.feng.eduservice.mapper.EduCourseMapper;
import com.feng.eduservice.service.EduCourseDescriptionService;
import com.feng.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author MrFeng
 * @since 2021-04-03
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    /**
     * 保存课程信息和课程描述信息
     * @param courseInfoVo
     * @return
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo)
    {
        //向课程表添加课程信息
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int row = baseMapper.insert(eduCourse);
        if (row==0){
            //添加失败
            throw new GuliException(20001,"添加课程信息失败！");
        }

        //获取添加成功的课程ID
        String courseId = eduCourse.getId();

        //向课程简介表添加课程简介信息
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(courseId); // 课程表ID和课程描述表ID关联

        eduCourseDescriptionService.save(eduCourseDescription);
        return courseId;
    }
}
