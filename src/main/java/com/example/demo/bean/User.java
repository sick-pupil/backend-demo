package com.example.demo.bean;

/**
 * @author lhy
 * @create 2023-11-14 09:53
 * @description
 **/

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.bean.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author lhy
 * @create 2023-11-14 09:54
 * @description
 **/
@Data
public class User extends Model<User> implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean accountLocked;

    private Boolean accountExpired;

    private Boolean credentialsExpired;

    private Set<Role> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
