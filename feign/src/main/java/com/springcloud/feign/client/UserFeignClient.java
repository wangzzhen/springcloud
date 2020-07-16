package com.springcloud.feign.client;

import com.springcloud.feign.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("user-service")
// 如果不使用服务发现，则可以如下直接指定请求的URL
//@FeignClient(name = "user-service", url = "http://localhost:8675/user")
public interface UserFeignClient {

    //注意这里的请求路径要与user-service端一致
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id);

    //多参数情况
    @GetMapping("/user/getByMap")
    User getUserByMap(@RequestParam Map<String,Object> map);

    //post情况
    @PostMapping("/user/save")
    User save(@RequestBody User user);
}
