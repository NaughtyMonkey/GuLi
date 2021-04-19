package com.feng.eduservice.controller;

import com.feng.commonutils.ResultEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(description = "登录管理")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin  //解决跨域
public class EduLoginController
{

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login")
    public ResultEntity login()
    {
        return ResultEntity.ok().data("token", "admin");
    }

    @GetMapping("info")
    public ResultEntity info()
    {
        return ResultEntity.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
