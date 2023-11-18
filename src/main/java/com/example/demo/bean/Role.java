package com.example.demo.bean;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author lhy
 * @create 2023-11-14 09:54
 * @description
 **/
@Data
public class Role extends Model<Role> implements GrantedAuthority, Serializable {


    private static final long serialVersionUID = 7914768579007691126L;

    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            if(o == null || !(o instanceof Role)) {
                return false;
            } else {
                return this.role.equals(((Role) o).getRole());
            }
        }
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }
}
