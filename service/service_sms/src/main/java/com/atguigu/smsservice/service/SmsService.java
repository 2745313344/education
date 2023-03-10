package com.atguigu.smsservice.service;

import org.springframework.stereotype.Component;

@Component
public interface SmsService {
     void Send(String phone, String code);
}
