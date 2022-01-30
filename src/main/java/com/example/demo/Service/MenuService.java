package com.example.demo.Service;

import com.example.demo.Model.MenuBean;

import java.util.List;

public interface MenuService {

    List<MenuBean> getAllMenu();

    MenuBean findByMenu_name(String menu_name);

    List<String> findPermsByUserId(Long Id);
}
