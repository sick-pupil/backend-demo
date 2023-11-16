package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.bean.UserInfo;

/**
 * @author lhy
 * @create 2023-11-07 13:59
 * @description
 **/
public interface DynamicDsService extends IService<UserInfo> {

    UserInfo selectUserInfoFromMysql();

    UserInfo selectUserInfoFromPostgres();
}
