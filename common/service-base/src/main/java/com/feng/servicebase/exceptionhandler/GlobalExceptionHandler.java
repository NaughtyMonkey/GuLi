package com.feng.servicebase.exceptionhandler;

import com.feng.commonutils.ResultEntity;
import com.feng.servicebase.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ControllerAdvice，是Spring3.2提供的新注解,它是一个Controller增强器,可对controller中被 @RequestMapping注解的方法加一些逻辑处理。最常用的就是异常处理
 *
 * 统一异常处理
 * 需要配合@ExceptionHandler使用。
 * 当将异常抛到controller时,可以对异常进行统一处理,规定返回的json格式或是跳转到一个错误页面
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
    /**
     * 指定出现什么异常执行这个方法
     * 因为不是在Controller中所有需要@ResponseBody返回Json
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultEntity error(Exception e)
    {
        e.printStackTrace();
        return ResultEntity.error().message("执行了全局异常处理");
    }

    /**
     * 特定异常，算数异常
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResultEntity error(ArithmeticException e){
        e.printStackTrace();
        return ResultEntity.error().message("执行了算数异常！");
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResultEntity error(GuliException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return ResultEntity.error().code(e.getErrorCode()).message(e.getMsg());
    }
}
