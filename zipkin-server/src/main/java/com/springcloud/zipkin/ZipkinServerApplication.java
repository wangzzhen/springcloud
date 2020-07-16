package com.springcloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/*
这种方式配置zipkin server尽量在低版本的springboot中进行，不然有可能出现版本不兼容问题（官方已经不推荐这种方式）
官网目前有三种安装方式：使用docker容器；下载jar；下载源代码运行。
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
