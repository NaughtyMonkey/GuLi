package com.feng.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * exclude = DataSourceAutoConfiguration.class
 * 默认不去加载数据库配置，不加启动服务不成功
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.feng"})
public class OssApplication
{
    public static void main(String[] args) {
            SpringApplication.run(OssApplication.class, args);
        }
}
