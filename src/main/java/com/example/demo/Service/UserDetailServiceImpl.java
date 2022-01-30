package com.example.demo.Service;

import com.example.demo.Dao.UserRepository;
import com.example.demo.Model.UserBean;
import com.example.demo.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 5.1根據用戶名去查詢這個用戶對應的權限訊息
 * ImMemoryUserDetailsManager是在內存中查找
 * 把對應的用戶包含權限訊息，封裝成UserDetails物件
 */
@Configuration
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    //查詢用戶訊息

    //查詢對應的用戶訊息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查詢
        UserBean userBean =userService.findByUsername(username);
        //如果沒有查詢到就拋出異常
        if(Objects.isNull(userBean)){
            throw new UsernameNotFoundException("找不到帳號，或是密碼錯誤");
        }

        //5.2把UserBean物件封裝成UserDetails
        //第一個方法是寫一個LoginUser累繼承UserDetails，或是直接在UserBean繼承UserDetails
        //6.返回UserDetails物件

        //查詢權限訊息
        List<String> list=menuService.findPermsByUserId(userBean.getId());//new ArrayList<>(Arrays.asList("test","admin"));
        return new LoginUser(userBean,list);
    }

}
