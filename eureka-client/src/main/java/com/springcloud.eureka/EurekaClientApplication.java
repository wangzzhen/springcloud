package com.springcloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

	@RestController
	static class TestController {

		@Autowired
		private DiscoveryClient discoveryClient;

		//查看客户端实例信息，applicationName为配置文件中的application.name
		@GetMapping("/{applicationName}")
		public List<ServiceInstance> getServiceInstanceByApplicationName(
				@PathVariable String applicationName){
			return discoveryClient.getInstances(applicationName);

		}
	}
}
