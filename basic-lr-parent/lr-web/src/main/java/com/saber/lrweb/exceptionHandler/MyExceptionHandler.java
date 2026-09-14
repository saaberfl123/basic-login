package com.saber.lrweb.exceptionHandler;

import com.saber.lrcommon.module.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler
{
    //获取日志对象
    private final Logger log= LoggerFactory.getLogger(MyExceptionHandler.class);
    @ExceptionHandler
    public ResponseEntity<Result> exceptionHandler(Exception ex)
    {
        log.error("处理请求发生异常 原因...",ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result(500,"服务器繁忙",null));
    }
}
