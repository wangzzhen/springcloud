package com.springcloud.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
Zuul自身就带有Hystrix，但是它监控的粒度是微服务级别，而不是某个API，
当某个API不可用时，会统一抛500错误码的异常。
我们可以为Zuul添加回退，以实现更友好的返回信息，这里是实现FallbackProvider接口
 */
@Component
public class UserFallbackProvider implements FallbackProvider {

    //表明是为哪个微服务提供回退
    @Override
    public String getRoute() {
        return "user-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            // fallback时的状态码
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            // 数字类型的状态码
            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode() .value();
            }

            //状态码文本信息
            @Override
            public String getStatusText() throws IOException {
                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            //响应体
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(
                        "User Service is not avaiable, please try again later".getBytes());
            }

            //响应头设置
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return httpHeaders;
            }
        };
    }
}
