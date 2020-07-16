package com.springcloud.zuul;

import com.springcloud.zuul.filter.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/*
生产环境中一般都需要部署高可用的Zuul以避免单点故障，实际开发中有两种情况：
1. Zuul的客户端也注册到了Eureka Server上（比如：MVC App等）
此时Zuul的高可用和其他微服务没区别，都是借助Eureka和Ribbon来实现负载均衡。
2.Zuul的客户端未注册到Eureka Server上（比如手机App端等）
现实中这种场景或许更常见，此时需要借助一个额外的负载均衡器来实现Zuul的高可用，比如：Nginx、HAProxy以及F5等。
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    //注入过滤器
    @Bean
    public PreFilter preFilter(){
        return new PreFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
