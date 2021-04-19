package com.feng.eduservice.controller;


import com.feng.commonutils.ResultEntity;
import com.feng.eduservice.entity.EduChapter;
import com.feng.eduservice.entity.chapter.ChapterVo;
import com.feng.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author MrFeng
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController
{

    @Autowired
    private EduChapterService chapterService;

    /**
     * 课程大纲列表，根据课程id进行查询
     *
     * @return
     */
    @GetMapping("getChapterVideo/{courseId}")
    public ResultEntity getCourseInfo(@PathVariable String courseId)
    {
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        return ResultEntity.ok().data("allChapterVideo",chapterVoList);
    }

    /**
     * 添加章节
     *
     * @param eduChapter
     * @return
     */
    @PostMapping("addChapter")
    public ResultEntity addChapter(@RequestBody EduChapter eduChapter)
    {
        chapterService.save(eduChapter);
        return ResultEntity.ok();
    }

    /**
     * 根据章节ID查询章节信息
     *
     * @param chapterId
     * @return
     */
    @GetMapping("getChapterInfo/{chapterId}")
    public ResultEntity getChapterInfoById(@PathVariable String chapterId)
    {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return ResultEntity.ok().data("eduChapter", eduChapter);
    }

    /**
     * 修改章节
     *
     * @param eduChapter
     * @return
     */
    @PostMapping("updateChapter")
    public ResultEntity updateChapter(@RequestBody EduChapter eduChapter)
    {
        chapterService.updateById(eduChapter);
        return ResultEntity.ok();
    }

    /**
     * 删除章节
     *
     * @param chapterId
     * @return
     */
    @DeleteMapping("{chapterId}")
    public ResultEntity deleteChapter(@PathVariable String chapterId)
    {
        boolean flag = chapterService.deleteChapter(chapterId);

        if (flag)
        {
            return ResultEntity.ok();
        }
        else
        {
            return ResultEntity.error();
        }
    }

}

