package com.example.demo.service.impl;

//import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.UserInfo;
import com.example.demo.mapper.DynamicDsMapper;
import com.example.demo.service.DynamicDsService;
import org.springframework.stereotype.Service;

/**
 * @author lhy
 * @create 2023-11-07 14:00
 * @description
 **/
@Service
public class DynamicDsServiceImpl extends ServiceImpl<DynamicDsMapper, UserInfo> implements DynamicDsService {

    @Override
//    @DS("mysql")
    public UserInfo selectUserInfoFromMysql() {
        return baseMapper.selectById(1000L);
    }

    @Override
//    @DS("postgresql")
    public UserInfo selectUserInfoFromPostgres() {
        return baseMapper.selectById(1L);
    }
}
