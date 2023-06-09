package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");

    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");

    }

//    @ExceptionHandler(GuliException.class)
//    @ResponseBody
//    public R error(GuliException e){
//        e.printStackTrace();
////        return R.error().message("执行了自定义异常处理");
//        return R.error().code(e.getCode()).message(e.getMsg());
//
//    }
}
