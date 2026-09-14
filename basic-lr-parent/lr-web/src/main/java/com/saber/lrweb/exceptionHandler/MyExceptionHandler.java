package com.saber.lrweb.exceptionHandler;

import com.saber.lrcommon.module.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<Result> exceptionHandler(Exception ex)
    {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result(500,"服务器繁忙",null));
    }
}
