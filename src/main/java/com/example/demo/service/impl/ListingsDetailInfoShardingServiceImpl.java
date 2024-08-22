package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.ListingsDetailInfo;
import com.example.demo.bean.ListingsDetailInfoSharding;
import com.example.demo.mapper.ListingsDetailInfoShardingMapper;
import com.example.demo.service.ListingsDetailInfoService;
import com.example.demo.mapper.ListingsDetailInfoMapper;
import com.example.demo.service.ListingsDetailInfoShardingService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【listings_detail_info】的数据库操作Service实现
* @createDate 2024-08-22 02:07:56
*/
@DS("sharding_readwrite_splitting_airbnb_db")
@Service
public class ListingsDetailInfoShardingServiceImpl extends ServiceImpl<ListingsDetailInfoShardingMapper, ListingsDetailInfoSharding>
    implements ListingsDetailInfoShardingService {

}




