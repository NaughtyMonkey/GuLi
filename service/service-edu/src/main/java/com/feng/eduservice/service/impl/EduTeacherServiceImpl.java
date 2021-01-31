package com.feng.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.eduservice.entity.EduTeacher;
import com.feng.eduservice.entity.TeacherQuery;
import com.feng.eduservice.mapper.EduTeacherMapper;
import com.feng.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author MrFeng
 * @since 2021-01-24
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery)
    {
        //构建查询条件
        QueryWrapper queryWrapper=new QueryWrapper();

        if (teacherQuery == null)
        {
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }

        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name))
        {
            //讲师名字模糊查询
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level))
        {
            //讲师等级，eq是=
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin))
        {
            //讲师入职时间,ge是>=
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end))
        {
            //讲师入职时间，le是<=
            queryWrapper.le("gmt_create",end);
        }

        //条件查询参数都已经设置好了
        baseMapper.selectPage(pageParam,queryWrapper);
    }
}
