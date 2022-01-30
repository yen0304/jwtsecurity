package com.example.demo.Service;


import com.example.demo.Dao.MenuRepository;
import com.example.demo.Model.MenuBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuRepository menuRepository;


    @Override
    public MenuBean findByMenu_name(String menuname) {

        return menuRepository.findByMenuname(menuname);
    }

    @Override
    public List<MenuBean> getAllMenu() {
        return null;
    }

    @Override
    public List<String> findPermsByUserId(Long userId) {
        return menuRepository.findPermsByUserId(userId);
    }
}
