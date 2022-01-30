package com.example.demo.filter;


//定義jwt認證過濾器

import com.example.demo.domain.LoginUser;
import com.example.demo.utils.JWTutils;
import com.example.demo.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFIlter extends OncePerRequestFilter {


    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //獲取token
        String token=request.getHeader("token");
        System.out.println("header-token:" + token);
        if (!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request,response);
            return; //不要往下解析
        }

        //解析token
        String userId;
        try{
            Claims claims = JWTutils.parseJWT(token);
            userId = claims.getSubject();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        //從redis中獲取用戶訊息
        String redisKey ="token:" +userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw  new RuntimeException("用戶未登錄");
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken
        //principal 用戶 因為到這裡已經認證通過了
        //credentials 一般放密碼（因為到這裡已經認證通過了）
                //TODO獲取權限訊息到authentication裡面
                //=new UsernamePasswordAuthenticationToken(loginUser,null,null);//第一個是用戶訊息，第三個參數是權限
                =new UsernamePasswordAuthenticationToken(loginUser,null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request,response);
    }
}
