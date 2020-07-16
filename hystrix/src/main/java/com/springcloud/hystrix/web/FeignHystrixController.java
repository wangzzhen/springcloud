package com.springcloud.hystrix.web;

import com.springcloud.hystrix.feign.UserFeignClient;
import com.springcloud.hystrix.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//feign使用hystrix，不需要RestTemplate，直接设置FeignClient中的fallback或fallbackFactory属性
@RestController
public class FeignHystrixController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/feign/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userFeignClient.getUserById(id);
    }
}
