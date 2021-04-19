package com.feng.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.eduservice.entity.EduSubject;
import com.feng.eduservice.entity.excel.SubjectData;
import com.feng.eduservice.entity.subject.OneSubject;
import com.feng.eduservice.entity.subject.TwoSubject;
import com.feng.eduservice.listener.SubjectExcelListener;
import com.feng.eduservice.mapper.EduSubjectMapper;
import com.feng.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *添加课程模块业务
 *
 * @author MrFeng
 * @since 2021-03-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService
{

    /**
     * 添加课程分类
     *
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService)
    {
        try
        {
            /**
             * 读取Excel操作
             */
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 获取课程分类一级二级分类数据
     * @return
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject()
    {
        //查询所有的一级分类，parent_id=0
        QueryWrapper<EduSubject> oneWrapper=new QueryWrapper<>();
        oneWrapper.eq("parent_id",0);
        List<EduSubject> oneSubjects = baseMapper.selectList(oneWrapper);

        //查询所有的二级分类
        QueryWrapper<EduSubject> twoWrapper=new QueryWrapper<>();
        twoWrapper.ne("parent_id",0);
        List<EduSubject> twoSubjects = baseMapper.selectList(twoWrapper);

        //创建list集合，用于存放最终封装的数据
        List<OneSubject> finalSubjectList=new ArrayList<>();

        //封装一级分类
        for (EduSubject oneEduSubject : oneSubjects)
        {
            OneSubject oneSubject=new OneSubject();

            // 根据目标对象oneSubject的属性，从源对象eduSubject取值并赋值
            BeanUtils.copyProperties(oneEduSubject,oneSubject);
            finalSubjectList.add(oneSubject);

            //将二级分类封装到对应的一级分类中
            List<TwoSubject> finalTwoSubject=new ArrayList<>();
            for (EduSubject twoEduSubject : twoSubjects)
            {
                //判断二级分类的parent_id和一级分类id是否一样
                if(twoEduSubject.getParentId().equals(oneEduSubject.getId())){
                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject,twoSubject);
                    finalTwoSubject.add(twoSubject);
                }

            }
            //把一级分类下面的所有二级分类，封装到对应的一级分类中
            oneSubject.setChildren(finalTwoSubject);
        }
        return finalSubjectList;
    }
}
