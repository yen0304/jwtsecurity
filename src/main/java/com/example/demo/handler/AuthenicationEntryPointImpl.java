package com.example.demo.handler;


import com.alibaba.fastjson.JSON;
import com.example.demo.domain.ResponseResult;
import com.example.demo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//自定義實現類，在config做配置

@Component
public class AuthenicationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"使用者認證失敗，請重新登入");
        String json=JSON.toJSONString(result);

        WebUtils.renderString(response,json);
    }
}
