package com.feng.servicebase.exceptionhandler;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * @Data只生成无参构造，当添加@AllArgsConstructor后无参构造会消失，所以要加@NoArgsConstructor生成无参构造
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException
{
    private Integer errorCode;//异常状态码

    private String msg;//异常信息
}
