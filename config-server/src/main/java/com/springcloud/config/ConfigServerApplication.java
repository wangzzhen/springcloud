package com.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
Config Server的高可用：涉及到Git仓库的高可用、RabbitMQ或kafaka的高可用以及Config Server自身的高可用。
Git仓库的高可用：可以自建高可用Git仓库如GitLab
Config Server自身的高可用：也可以分为未注册到Eureka和注册到Eureka两种情形，与zuul高可用类似。
关于统一配置中心，还可以选择更好用的Apollo（携程的开源项目）
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
