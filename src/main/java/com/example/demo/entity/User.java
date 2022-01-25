package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String status;
    private String email;
    private String phonenumber;
    private String Sex;
    private String avatar;
    private String usertype;
    private Long creteBy;
    private Date creteTime;
    private Long UpdateBy;
    private Integer delFlag;
}
