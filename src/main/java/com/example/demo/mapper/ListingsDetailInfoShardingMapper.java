package com.example.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.bean.ListingsDetailInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.ListingsDetailInfoSharding;

/**
* @author Administrator
* @description 针对表【listings_detail_info】的数据库操作Mapper
* @createDate 2024-08-22 02:07:56
* @Entity com.example.demo.bean.ListingsDetailInfo
*/
@DS("sharding_readwrite_splitting_airbnb_db")
public interface ListingsDetailInfoShardingMapper extends BaseMapper<ListingsDetailInfoSharding> {

}




