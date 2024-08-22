package com.example.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.bean.ReviewsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.ReviewsInfoSharding;

/**
* @author Administrator
* @description 针对表【reviews_info】的数据库操作Mapper
* @createDate 2024-08-22 02:09:57
* @Entity com.example.demo.bean.ReviewsInfo
*/
@DS("sharding_readwrite_splitting_airbnb_db")
public interface ReviewsInfoShardingMapper extends BaseMapper<ReviewsInfoSharding> {

}




