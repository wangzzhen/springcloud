package com.springcloud.feign.web;

import com.springcloud.feign.client.UserFeignClient;
import com.springcloud.feign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FeignController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userFeignClient.getUserById(id);
    }

    @GetMapping("/user/{name}&{age}")
    public User getUserByMap(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        return userFeignClient.getUserByMap(map);
    }

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {
        return userFeignClient.save(user);
    }
}
