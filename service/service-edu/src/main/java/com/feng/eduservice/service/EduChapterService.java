package com.feng.eduservice.service;

import com.feng.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author MrFeng
 * @since 2021-04-03
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
