package com.example.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.bean.ListingsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.ListingsInfoSharding;

/**
* @author Administrator
* @description 针对表【listings_info】的数据库操作Mapper
* @createDate 2024-08-22 02:09:07
* @Entity com.example.demo.bean.ListingsInfo
*/
@DS("sharding_readwrite_splitting_airbnb_db")
public interface ListingsInfoShardingMapper extends BaseMapper<ListingsInfoSharding> {

}




