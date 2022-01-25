package com.example.demo.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * JPA映射
 *
 */
@Getter
@Setter
@Entity
@Table(name= "sys_USER")
public class UserBean {
    @Id //@Id 因為id是一個primary key（獨一無二）
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動增加，表示id自動生成

    @Column(name = "Id")
    private Long Id;

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Integer status;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "Sex")
    private String Sex;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "usertype")
    private String usertype;

    @Column(name = "create_User")
    private Long create_User;

    @Column(name="create_Time")
    private Date create_Time;

    @Column(name = "update_User")
    private Long update_User;

    @Column(name = "update_Time")
    private Date update_Time;

    @Column(name = "delflag") //mysql不分大小寫 大寫會出錯
    private Integer delFlag;
    //JPA預設會將Entity類別的所有屬性與資料表的欄位做映射，
    // 如果要忽略某個屬性，則在該屬性掛上@Transient annotation。


}
