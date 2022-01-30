package com.example.demo.Service;

import com.example.demo.Model.UserBean;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.ResponseResult;

import com.example.demo.utils.JWTutils;
import com.example.demo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LoginServiceImpl implements LoginService{


    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(UserBean userBean) {
        //AuthenticationManager authenticate進行用戶認證

        //1先封裝成authenticationToken對象
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(userBean.getUsername(),userBean.getPassword());
        //調用authenticationManager認證
         Authentication authentication =authenticationManager.authenticate(authenticationToken);
        //如果認證沒通過
        if(Objects.isNull(authentication)){
            throw new ArithmeticException("登入失敗");
        }
        //通過，使用userid生成jwt,生成jwt後存入Responseresult類別
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        String userId =loginUser.getUserBean().getId().toString();
        String jwt =JWTutils.creatJWT(userId);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //再把完整的用戶訊息存入redis userid作為key，token:是自定義前綴
        redisCache.setCacheObject("token:" + userId,loginUser);
        //token是直接返回給用戶的，之後前端的所有請求都要戴上token，否則請求失敗
        return new ResponseResult(200,"登入成功",map);
    }
}
