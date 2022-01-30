package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //設置允許跨域的URL
        registry.addMapping("/**")
                //設置允許請求的域名
                .allowedOrigins("*")
                //是否允許cookie
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedHeaders("*")
                //跨域允許時間
                .maxAge(3600);
    }
}
