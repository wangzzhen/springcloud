package com.springcloud.ribbon.web;

import com.springcloud.ribbon.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    private static final Logger log = LoggerFactory.getLogger(RibbonController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${userService.url}")
    private String userServiceUrl;

    //从user-service获取用户数据
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + id, User.class);
    }

    //查看选用的service实例日志
    @GetMapping("/log-instance")
    public void getInstanceLogInfo(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
        log.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(),
                serviceInstance.getPort());
    }
}
