package com.feng.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feng.eduservice.entity.EduChapter;
import com.feng.eduservice.entity.EduVideo;
import com.feng.eduservice.entity.chapter.ChapterVo;
import com.feng.eduservice.entity.chapter.VideoVo;
import com.feng.eduservice.mapper.EduChapterMapper;
import com.feng.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feng.eduservice.service.EduVideoService;
import com.feng.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author MrFeng
 * @since 2021-04-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService
{
    @Autowired
    private EduVideoService videoService;

    /**
     * 根据课程ID查询课程
     *
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId)
    {
        // 根据课程ID从chapter表中查询符合条件的章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterWrapper);

        // 根据课程ID从chapter表中查询符合条件的章节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        List<EduVideo> videoList = videoService.list(videoWrapper);

        List<ChapterVo> chapterVoList = new ArrayList<>();

        for (EduChapter chapter : chapterList)
        {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);

            List<VideoVo> videoVoList = new ArrayList<>();

            for (EduVideo eduVideo : videoList)
            {
                if (eduVideo.getChapterId().equals(chapter.getId()))
                {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return chapterVoList;
    }

    @Override
    public boolean deleteChapter(String chapterId)
    {
        //根据章节ID查询小节，如果有小节不删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0)
        {
            throw new GuliException(20001, "不能删除");
        }
        else
        {
            int i = baseMapper.deleteById(chapterId);
            //成功 i>0 0>0
            return i > 0;
        }
    }
}
