package com.example.demo.config;

import com.example.demo.filter.JwtAuthenticationTokenFIlter;
import com.example.demo.handler.AccessDeniedHandlerImpl;
import com.example.demo.handler.AuthenicationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 在實際上，不會把密碼的明文存在資料庫裡面，所以springsecurity一般提供的BCrtptpasswordEnconder
 * 注入spring容器，springsecurity就會使用這個解碼器來做密碼校驗，所以就新增一個類別並繼承WebSecurityConfigurerAdapter
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFIlter jwtAuthenticationTokenFIlter;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Autowired
    private AuthenicationEntryPointImpl authenicationEntryPoint;

    //訂製一個方法來返回BCryptPasswordEncoder()
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //配置

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() //允許匿名訪問
                .antMatchers("/user/login").anonymous()
                //除了上面以外都需要認證
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        /*
        public HttpSecurity addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter) {
        this.comparator.registerBefore(filter.getClass(), beforeFilter);
        return this.addFilter(filter);
    }
         */
        //加過濾器
        http.addFilterBefore(jwtAuthenticationTokenFIlter, UsernamePasswordAuthenticationFilter.class);

        //配置異常處理器
        http
                .exceptionHandling()
                .authenticationEntryPoint(authenicationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.cors(); //允許跨域
    }
}
