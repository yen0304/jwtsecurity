package com.example.demo.Dao;

import com.example.demo.Model.MenuBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuRepository extends CrudRepository<MenuBean,Long> {

    //方法名不行加底線spring報錯"No property ... found for type..."
    MenuBean findByMenuname(String menuname);

    @Query(value = "SELECT DISTINCT sys_menu.perms " +
            "FROM sys_user_role LEFT JOIN sys_role ON sys_user_role.role_id = sys_role.Id " +
            "LEFT JOIN sys_role_menu ON sys_role.Id = sys_role_menu.role_id " +
            "LEFT JOIN sys_menu ON sys_role_menu.menu_id=sys_menu.Id " +
            "WHERE user_id =? AND sys_role.status=0",nativeQuery = true)
    List<String> findPermsByUserId(Long userId);
    //在問號後面加上數字，指定要載入第幾個參數得值
}
