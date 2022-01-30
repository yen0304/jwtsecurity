package com.example.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.Model.UserBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private UserBean userBean;

    private List<String> permissions;

    @JSONField(serialize = false) //不進行序列化(不加進去redis，redis為了安全不會把SimpleGrantedAuthority存進去，加了會出錯)
    private List<SimpleGrantedAuthority> authorities;         //為了避免每次都要進行遍歷，所以定義一個變數authorities

    public LoginUser(UserBean userBean, List<String> permissions) {
        this.userBean = userBean;
        this.permissions = permissions;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //把permissions中string類型的權限進行封裝成SimpleGrantedAuthority
        /*
        public final class SimpleGrantedAuthority implements GrantedAuthority {
        private static final long serialVersionUID = 530L;
        private final String role;

        public SimpleGrantedAuthority(String role) {
            Assert.hasText(role, "A granted authority textual representation is required");
            this.role = role;
        }
         */
        if (authorities != null) {

            return authorities;
        }
        //遍歷(方法一)

        authorities = new ArrayList<>();
        for(String permission : permissions){
            SimpleGrantedAuthority authority =new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }

        //方法二
        // authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return userBean.getPassword();
    }

    @Override
    public String getUsername() {
        return userBean.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
