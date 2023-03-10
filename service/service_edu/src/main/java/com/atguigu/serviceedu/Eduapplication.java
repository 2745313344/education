package com.atguigu.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient    //nacos注册
@EnableFeignClients
@ComponentScan(basePackages = {"com.atguigu"})
public class Eduapplication {
    public static void main(String[] args) {
        SpringApplication.run(Eduapplication.class,args);
    }
}
