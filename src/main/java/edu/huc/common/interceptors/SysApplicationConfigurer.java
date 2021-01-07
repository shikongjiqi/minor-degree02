package edu.huc.common.interceptors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SysApplicationConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registryAdmin) {
        System.out.println("请求拦截");
        registryAdmin
                .addInterceptor(AllLoginInterceptor())
                .addPathPatterns("/**")
                .addPathPatterns("/static/**");
//                .excludePathPatterns("/static/index.html")
//                .excludePathPatterns("/logout/**")
//                .excludePathPatterns("/favicon.ico")
//                .excludePathPatterns("/public/**");
    }

    @Bean
    public SysLoginInterceptor AdminLoginInterceptor() {
        return new SysLoginInterceptor();
    }

    @Bean
    public UserIdInterceptor AllLoginInterceptor() {
        return new UserIdInterceptor();
    }
}
