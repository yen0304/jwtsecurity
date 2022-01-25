package com.example.demo.Service;

import com.example.demo.Model.UserBean;
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
}