package com.feng.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.eduservice.entity.EduSubject;
import com.feng.eduservice.entity.excel.SubjectData;
import com.feng.eduservice.service.EduSubjectService;
import com.feng.servicebase.exceptionhandler.GuliException;

/**
 * 读取Excel监听器
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData>
{
    //因为读Excel操作传入new SubjectExcelListener()，所以此类不能交给spring容器管理
    public EduSubjectService subjectService;

    public SubjectExcelListener()
    {
    }

    public SubjectExcelListener(EduSubjectService subjectService)
    {
        this.subjectService = subjectService;
    }

    /**
     * 读取excel内容，一行一行进行读取
     *
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext)
    {
        if (subjectData == null)
        {
            throw new GuliException(20001, "文件数据为空");
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断数据库中是否已存在一级分类，存在就不保存
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null)
        { //没有相同一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }

        //获取一级分类id值
        //1、如果表中存在一级分类直接取出
        //2、如果表中不存在，添加之后再取出
        String pid = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null)
        {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }

    //判断数据库中是否已存在一级分类
    private EduSubject existOneSubject(EduSubjectService subjectService, String name)
    {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断数据库中是否已存在二级分类
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid)
    {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext)
    {

    }
}
