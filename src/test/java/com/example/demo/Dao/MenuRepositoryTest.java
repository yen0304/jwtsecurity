package com.example.demo.Dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void test(){
        System.out.println(menuRepository.findById(2L));
        assertEquals("測試",menuRepository.findByMenuname("測試").getMenuname());
        System.out.println(menuRepository.findPermsByUserId(2L));
        //System.out.println(menuRepository.findByMenu_name("測試"));
    }

}