package com.example.demo.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name= "sys_menu")
public class MenuBean{

    @Id //@Id 因為id是一個primary key（獨一無二）
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動增加，表示id自動生成
    @Column(name = "Id")
    private Long Id;

    @Column(name = "menu_name")
    private String menuname;

    @Column(name = "path")
    private String path;

    @Column(name = "component")
    private String component;

    @Column(name = "visible")
    private String visible;

    @Column(name = "status")
    private Integer status;

    @Column(name = "perms")
    private String perms;

    @Column(name = "icon")
    private String icon;

    @Column(name = "delflag") //mysql不分大小寫 大寫會出錯
    private Integer delFlag;

    @Column(name = "create_User")
    private Long create_User;

    @Column(name="create_Time")
    private Date create_Time;

    @Column(name = "update_User")
    private Long update_User;

    @Column(name = "update_Time")
    private Date update_Time;

    @Column(name = "remark")
    private String remark;
    //JPA預設會將Entity類別的所有屬性與資料表的欄位做映射，
    // 如果要忽略某個屬性，則在該屬性掛上@Transient annotation。
}
