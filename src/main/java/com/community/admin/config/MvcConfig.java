package com.community.admin.config;

import com.community.admin.interceptor.LoginInterceptor;
import com.community.admin.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname MvcConfig
 * @Description 全局配置
 * @Date 2021/10/12 18:03
 * @Created by thx
 */
@Component
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //    添加拦截器，除了登录路径，其他全部拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(GlobalConstant.SERVER_URL_PREFIX + "login/**");
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }


    /**
     * 文件上传路径前缀
     */
    @Value("${file.path.prefix}")
    public String filePrefix;
    /**
     * 本地磁盘目录
     */
    @Value("${file.path.upload}")
    public String uploadLocalPath;

    /**
     * 映射本地磁盘为静态目录
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        FileUtil.filePrefix = filePrefix;
        FileUtil.uploadLocalPath = uploadLocalPath;
        registry.addResourceHandler(filePrefix + "/**").addResourceLocations("file:" + uploadLocalPath);
    }

    /**
     * 解决端口跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问资源定义： 所有资源
        registry.addMapping("/**")
//                   只允许本地的指定端口访问
                .allowedOrigins("*")
                // 允许发送凭证: 前端如果配置改属性为true之后，则必须同步配置
                .allowCredentials(true)
                // 允许所有方法
                .allowedMethods("*");
    }
}
