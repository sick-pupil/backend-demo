package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.ReviewsDetailInfo;
import com.example.demo.bean.ReviewsDetailInfoSharding;
import com.example.demo.mapper.ReviewsDetailInfoShardingMapper;
import com.example.demo.service.ReviewsDetailInfoService;
import com.example.demo.mapper.ReviewsDetailInfoMapper;
import com.example.demo.service.ReviewsDetailInfoShardingService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【reviews_detail_info】的数据库操作Service实现
* @createDate 2024-08-22 02:09:41
*/
@DS("sharding_readwrite_splitting_airbnb_db")
@Service
public class ReviewsDetailInfoShardingServiceImpl extends ServiceImpl<ReviewsDetailInfoShardingMapper, ReviewsDetailInfoSharding>
    implements ReviewsDetailInfoShardingService {

}




