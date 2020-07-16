package com.springcloud.sleuth.web;

import com.springcloud.sleuth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SleuthController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${userService.url}")
    private String userServiceUrl;

    //从user-service获取用户数据
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + id, User.class);
    }

    //查看service实例日志
    @GetMapping("/log-instance")
    public List<ServiceInstance> getInstance(){
        return discoveryClient.getInstances("user-service");
    }
}
