package com.example.demo.Service;

import com.example.demo.Model.UserBean;
import com.example.demo.domain.ResponseResult;

public interface LoginService {
    ResponseResult login(UserBean userBean);

    ResponseResult logout();
}
