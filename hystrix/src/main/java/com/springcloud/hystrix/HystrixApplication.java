package com.springcloud.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients //feign方式使用Hystrix，Feign已默认整合了Hystrix
public class HystrixApplication {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    //注意当只使用Hystrix时这里 @LoadBalanced是必须要的，不然会找不到需要调用的服务（比如user-service）
    //feign方式使用hystrix时RestTemplate与@LoadBalanced都不需要
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return restTemplateBuilder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }

}
