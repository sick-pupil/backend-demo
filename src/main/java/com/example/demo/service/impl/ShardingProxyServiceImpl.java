package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.*;
import com.example.demo.mapper.*;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShardingProxyServiceImpl implements ShardingProxyService {

    @Resource
    private CalendarMapper calendarMapper;

    @Resource
    private ListingsDetailInfoMapper listingsDetailInfoMapper;

    @Resource
    private ListingsInfoMapper listingsInfoMapper;

    @Resource
    private NeighbourhoodMapper neighbourhoodMapper;

    @Resource
    private ReviewsDetailInfoMapper reviewsDetailInfoMapper;

    @Resource
    private ReviewsInfoMapper reviewsInfoMapper;

    @Autowired
    private CalendarShardingService calendarShardingService;

    @Autowired
    private ListingsDetailInfoShardingService listingsDetailInfoShardingService;

    @Autowired
    private ListingsInfoShardingService listingsInfoShardingService;

    @Autowired
    private NeighbourhoodShardingService neighbourhoodShardingService;

    @Autowired
    private ReviewsDetailInfoShardingService reviewsDetailInfoShardingService;

    @Autowired
    private ReviewsInfoShardingService reviewsInfoShardingService;

    @Resource
    private ReviewsInfoShardingMapper reviewsInfoShardingMapper;

    private static final Integer MIGRATE_PAGE_SIZE = 1000;

    private static final Integer MIGRATE_MAX_PAGE_NUM = 100;

    @Override
    public void migrateData() throws Exception {
        for(int i = 1; i <= MIGRATE_MAX_PAGE_NUM; i++) {
            List<ReviewsInfo> sourceReviewsInfos = reviewsInfoMapper.selectPage(new Page<>(i, MIGRATE_PAGE_SIZE), null).getRecords();
            if(CollectionUtils.isNotEmpty(sourceReviewsInfos)) {
                List<ReviewsInfoSharding> targetReviewsInfos = new ArrayList<>();
                for(ReviewsInfo sourceReviewsInfo : sourceReviewsInfos) {
                    ReviewsInfoSharding targetReviewsInfo = new ReviewsInfoSharding();
                    BeanUtils.copyProperties(targetReviewsInfo, sourceReviewsInfo);
                    targetReviewsInfos.add(targetReviewsInfo);
                }
                reviewsInfoShardingService.saveBatch(targetReviewsInfos);
            }
        }
    }
}
