package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.ReviewsInfo;
import com.example.demo.bean.ReviewsInfoSharding;
import com.example.demo.mapper.ReviewsInfoShardingMapper;
import com.example.demo.service.ReviewsInfoService;
import com.example.demo.mapper.ReviewsInfoMapper;
import com.example.demo.service.ReviewsInfoShardingService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【reviews_info】的数据库操作Service实现
* @createDate 2024-08-22 02:09:57
*/
@DS("sharding_readwrite_splitting_airbnb_db")
@Service
public class ReviewsInfoShardingServiceImpl extends ServiceImpl<ReviewsInfoShardingMapper, ReviewsInfoSharding>
    implements ReviewsInfoShardingService {

}




