package com.example.demo.Service;

import com.example.demo.Model.UserBean;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    public void getAllUser() {
        System.out.println(userServiceImpl.getAllUser());
    }

    @Test
    public void findUser(){
        UserBean bean =userServiceImpl.findByUsername("test123");
        assertEquals("test123",bean.getUsername());
        Optional<UserBean> b =userServiceImpl.findById(1L);
        bean=b.get();
        assertEquals("test123",bean.getUsername());
        assertEquals(1L,bean.getId());
    }

    @Test
    public  void  saveUser(){
        UserBean bean = new UserBean();
        bean.setUsername("abc123");
        bean.setPassword("abc123");
        bean.setNickname("ac3");
        bean.setEmail("abc123@gmail.com");
        bean.setPhonenumber("0912345678");
        bean.setSex("1");
        userServiceImpl.saveUser(bean);

        assertNotNull(userServiceImpl.findByUsername("abc123"));
    }
}