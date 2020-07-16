package com.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(PreFilter.class);

    /*
        对于Zuul的请求声明周期来说，一共有4种标准过滤器类型：
        PRE：在请求被路由之前调用，可利用这种过滤器实现身份验证、记录调试信息等操作；
        ROUTING：将请求路由到微服务，可利用这种过滤器用于构建发送给微服务的请求；
        POST：在路由到微服务以后执行，可用来为响应添加标准的HTTP Header、收集统计信息和指标、
        将响应从微服务发送给客户端等；
        ERROR：在其他阶段发生错误时执行该过滤器；
     */
    //返回过滤器的类型，可选项：pre, route, post, error
    @Override
    public String filterType() {
        return "pre";
    }

    //返回一个int值来指定过滤器的执行顺序,不同的过滤器允许返回相同的数字
    @Override
    public int filterOrder() {
        return 0;
    }

    //返回一个boolean值来判断该过滤器是否要执行
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤器具体逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info("请求方法：{},请求地址：{}", request.getMethod(), request.getRequestURL().toString());
        //token验证。如果请求参数里面没有token，则直接返回权限不足状态码
        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("No permissions, access denied");
        }
        return null;
    }
}
