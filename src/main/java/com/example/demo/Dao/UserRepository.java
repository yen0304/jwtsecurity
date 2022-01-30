package com.example.demo.Dao;

import com.example.demo.Model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserRepository extends JpaRepository<UserBean,Long>
{

    //按照命名規則來命名方法 JPA就會自動生成,返回值可以是UserBean或是List<UserBean>

    UserBean findByUsername(String username);


}
