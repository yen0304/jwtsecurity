package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 在實際上，不會把密碼的明文存在資料庫裡面，所以springsecurity一般提供的BCrtptpasswordEnconder
 * 注入spring容器，springsecurity就會使用這個解碼器來做密碼校驗，所以就新增一個類別並繼承WebSecurityConfigurerAdapter
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //訂製一個方法來返回BCryptPasswordEncoder()
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
