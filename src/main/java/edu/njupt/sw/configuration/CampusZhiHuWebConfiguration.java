package edu.njupt.sw.configuration;

import edu.njupt.sw.interceptor.LoginRequiredInterceptor;
import edu.njupt.sw.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Component  //实例化
public class CampusZhiHuWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired   //自动装配
            PassportInterceptor passportInterceptor;

    @Autowired   //自动装配
            LoginRequiredInterceptor loginRequredInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) { //注册自己的拦截器
        registry.addInterceptor(passportInterceptor); //添加自己的拦截器，添加到链路上
        registry.addInterceptor(loginRequredInterceptor).addPathPatterns("/user/*"); //若当前用户未登录，在其访问*/user/*时进行拦截，强制登录才可继续访问。
        super.addInterceptors(registry);
    }
}
