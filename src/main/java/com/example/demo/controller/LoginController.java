package com.example.demo.controller;

import com.example.demo.Model.UserBean;
import com.example.demo.Service.LoginService;
import com.example.demo.domain.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    @Autowired
    private LoginService loginService;

    /**
     * {
     *   "username":"abc123",
     *   "password":"abc123"
     * }
     * @param userBean
     * @return
     */
    @PostMapping ("/user/login")
    public ResponseResult login(@RequestBody UserBean userBean){
        return loginService.login(userBean);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
