package com.example.demo.Service;

import com.example.demo.Model.MenuBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MenuServiceImplTest {

    @Autowired
    MenuServiceImpl menuService;

    @Test
    public void findBy(){
        MenuBean menuBean=menuService.findByMenu_name("測試");
        System.out.println(menuService.findPermsByUserId(2L));
    }
}