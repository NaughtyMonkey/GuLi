package com.feng.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.feng"}) //为了扫描到swagger配置类
public class EduApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(EduApplication.class,args);
    }
}
