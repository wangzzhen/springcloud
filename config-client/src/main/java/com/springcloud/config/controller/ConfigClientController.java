package com.springcloud.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //手动刷新配置，@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
public class ConfigClientController {

    @Value("${scc}")
    private String profile;

    @GetMapping("/scc")
    public Object getProfile(){
        return profile;
    }
}
