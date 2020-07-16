package com.springcloud.hystrix.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.hystrix.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userService.url}")
    private String userServiceUrl;

    @HystrixCommand(fallbackMethod = "getUserByIdFallback")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + id, User.class);
    }

    public User getUserByIdFallback(Long id){
        return new User();
    }
}
