package com.feng.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.eduservice.entity.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author MrFeng
 * @since 2021-01-24
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 讲师条件分页查询
     *
     * @param pageParam 分页对象，分页参数和返回结果都封装到pageParam中
     * @param teacherQuery 条件查询参数
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
