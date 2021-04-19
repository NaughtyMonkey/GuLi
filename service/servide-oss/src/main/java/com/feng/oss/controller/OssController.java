package com.feng.oss.controller;

import com.feng.commonutils.ResultEntity;
import com.feng.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "上传头像")
@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
public class OssController
{
    @Autowired
    private OssService ossService;

    /**
     * 上传头像方法
     * @param file
     * @return
     */
    @ApiOperation(value="上传头像接口")
    @PostMapping
    public ResultEntity uploadOssFile(MultipartFile file){
        //返回访问头像在oss的路径
        String url=ossService.uploadFileAvatar(file);
        return ResultEntity.ok().data("url",url);
    }
}
