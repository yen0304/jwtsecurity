package com.example.demo.Service;


import com.example.demo.Dao.UserRepository;
import com.example.demo.Model.UserBean;
import com.example.demo.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityConfig securityConfig;

    @Override
    public List<UserBean> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserBean saveUser(UserBean bean){
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        bean.setPassword(passwordEncoder.encode(bean.getPassword()));
        return userRepository.save(bean);
    }

    @Override
    public UserBean UpdateUser(UserBean bean){
        if(findByUsername(bean.getUsername())!= null){
            return userRepository.save(bean);
        }
        return null;
    }

    @Override
    public UserBean findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserBean> findById(Long id) {
        return userRepository.findById(id);
    }
}
