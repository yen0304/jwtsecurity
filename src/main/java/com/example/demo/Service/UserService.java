package com.example.demo.Service;



import com.example.demo.Model.UserBean;


import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserBean> getAllUser();

    UserBean saveUser(UserBean bean);

    UserBean UpdateUser(UserBean bean);

    UserBean findByUsername(String username);

    Optional<UserBean> findById(Long id);

}
