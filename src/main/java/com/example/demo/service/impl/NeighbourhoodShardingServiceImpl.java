package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Neighbourhood;
import com.example.demo.bean.NeighbourhoodSharding;
import com.example.demo.mapper.NeighbourhoodShardingMapper;
import com.example.demo.service.NeighbourhoodService;
import com.example.demo.mapper.NeighbourhoodMapper;
import com.example.demo.service.NeighbourhoodShardingService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【neighbourhood】的数据库操作Service实现
* @createDate 2024-08-22 02:09:28
*/
@DS("sharding_readwrite_splitting_airbnb_db")
@Service
public class NeighbourhoodShardingServiceImpl extends ServiceImpl<NeighbourhoodShardingMapper, NeighbourhoodSharding>
    implements NeighbourhoodShardingService {

}




