package com.springcloud.hystrix.feign;

import com.springcloud.hystrix.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//feign使用hystrix，设置fallback或fallbackFactory属性
@FeignClient(name = "user-service", fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {

    //注意这里的请求路径要与user-service端一致
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id);
}
