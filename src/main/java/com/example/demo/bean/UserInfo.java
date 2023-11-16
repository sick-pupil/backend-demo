package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lhy
 * @create 2023-11-07 14:11
 * @description
 **/
@Data
@TableName("user_info")
public class UserInfo extends Model<UserInfo> implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("age")
    private Long age;

    @TableField("address")
    private String address;

    @TableField("email")
    private String email;
}
