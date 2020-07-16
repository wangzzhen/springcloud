package com.springcloud.hystrix.feign;

import com.springcloud.hystrix.model.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//fallback工厂类，Feign使用Hystrix时需要用到
@Component
public class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger log = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User getUserById(Long id) {
                //日志最好放在各个fallback中，而不要直接放在create方法中，不然在应用启动的时候就会打印日志
                log.info("fallback,reason:", throwable);
                return new User();
            }
        };
    }
}
