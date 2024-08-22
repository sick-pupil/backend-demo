package com.example.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.bean.Neighbourhood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.NeighbourhoodSharding;

/**
* @author Administrator
* @description 针对表【neighbourhood】的数据库操作Mapper
* @createDate 2024-08-22 02:09:28
* @Entity com.example.demo.bean.Neighbourhood
*/
@DS("sharding_readwrite_splitting_airbnb_db")
public interface NeighbourhoodShardingMapper extends BaseMapper<NeighbourhoodSharding> {

}




