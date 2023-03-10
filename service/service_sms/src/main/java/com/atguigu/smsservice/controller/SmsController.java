package com.atguigu.smsservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.smsservice.Utils.GetRandomNumber;
import com.atguigu.smsservice.service.SmsService;
import com.atguigu.smsservice.service.impl.SmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edusms/sms")
//@CrossOrigin
@ComponentScan("com.atguigu")
public class SmsController {
//    @Autowired
    private SmsService smsService = new SmsServiceImpl();
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private GetRandomNumber getRandomNumber;
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //1、从redis中获取验证码，如果获取直接返回
        String code = getRandomNumber.Number();
        String s = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(s)){
            return R.ok();
        }
        smsService.Send(phone,code);
        redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        return R.ok();
    }
}
