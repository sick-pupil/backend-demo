package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    private static final Long MIGRATE_PAGE_SIZE = 1000L;

    private static final Long MIGRATE_MAX_PAGE_NUM = 100L;

    @Override
    public void migrateData() throws Exception {
        log.info("迁移Calendar");
        for(long i = 1L; i <= MIGRATE_MAX_PAGE_NUM; i++) {
            List<Calendar> sourceCalendars = calendarMapper.selectPage(new Page<>(i, MIGRATE_PAGE_SIZE), null).getRecords();
            if(CollectionUtils.isNotEmpty(sourceCalendars)) {
                List<CalendarSharding> targetCalendars = new ArrayList<>();
                for(Calendar sourceCalendar : sourceCalendars) {
                    CalendarSharding targetCalendar = new CalendarSharding();
                    BeanUtils.copyProperties(targetCalendar, sourceCalendar);
                    targetCalendars.add(targetCalendar);
                }
                log.info("迁移Calendar, 迁移页码: {}, 迁移页大小: {}", i, targetCalendars.size());
                calendarShardingService.saveBatch(targetCalendars);
            }
        }

        log.info("迁移listings_detail_info");
        for(long i = 1L; i <= MIGRATE_MAX_PAGE_NUM; i++) {
            List<ListingsDetailInfo> sourceListingsDetailInfos = listingsDetailInfoMapper.selectPage(new Page<>(i, MIGRATE_PAGE_SIZE), null).getRecords();
            if(CollectionUtils.isNotEmpty(sourceListingsDetailInfos)) {
                List<ListingsDetailInfoSharding> targetListingsDetailInfos = new ArrayList<>();
                for(ListingsDetailInfo sourceListingsDetailInfo : sourceListingsDetailInfos) {
                    ListingsDetailInfoSharding targetListingsDetailInfo = new ListingsDetailInfoSharding();
                    BeanUtils.copyProperties(targetListingsDetailInfo, sourceListingsDetailInfo);
                    targetListingsDetailInfos.add(targetListingsDetailInfo);
                }
                log.info("迁移listings_detail_info, 迁移页码: {}, 迁移页大小: {}", i, targetListingsDetailInfos.size());
                listingsDetailInfoShardingService.saveBatch(targetListingsDetailInfos);
            }
        }

        log.info("迁移listings_info");
        for(long i = 1L; i <= MIGRATE_MAX_PAGE_NUM; i++) {
            List<ListingsInfo> sourceListingsInfos = listingsInfoMapper.selectPage(new Page<>(i, MIGRATE_PAGE_SIZE), null).getRecords();
            if(CollectionUtils.isNotEmpty(sourceListingsInfos)) {
                List<ListingsInfoSharding> targetListingsInfos = new ArrayList<>();
                for(ListingsInfo sourceListingsInfo : sourceListingsInfos) {
                    ListingsInfoSharding targetListingsInfo = new ListingsInfoSharding();
                    BeanUtils.copyProperties(targetListingsInfo, sourceListingsInfo);
                    targetListingsInfos.add(targetListingsInfo);
                }
                log.info("迁移listings_info, 迁移页码: {}, 迁移页大小: {}", i, targetListingsInfos.size());
                listingsInfoShardingService.saveBatch(targetListingsInfos);
            }
        }

        log.info("迁移neighbourhood");
        for(long i = 1L; i <= MIGRATE_MAX_PAGE_NUM; i++) {
            List<Neighbourhood> sourceNeighbourhoods = neighbourhoodMapper.selectPage(new Page<>(i, MIGRATE_PAGE_SIZE), null).getRecords();
            if(CollectionUtils.isNotEmpty(sourceNeighbourhoods)) {
                List<NeighbourhoodSharding> targetNeighbourhoods = new ArrayList<>();
                for(Neighbourhood sourceNeighbourhood : sourceNeighbourhoods) {
                    NeighbourhoodSharding targetNeighbourhood = new NeighbourhoodSharding();
                    BeanUtils.copyProperties(targetNeighbourhood, sourceNeighbourhood);
                    targetNeighbourhoods.add(targetNeighbourhood);
                }
                log.info("迁移neighbourhood, 迁移页码: {}, 迁移页大小: {}", i, targetNeighbourhoods.size());
                neighbourhoodShardingService.saveBatch(targetNeighbourhoods);
            }
        }

        log.info("迁移reviews_detail_info");
        for(long i = 1L; i <= MIGRATE_MAX_PAGE_NUM; i++) {
            List<ReviewsDetailInfo> sourceReviewsDetailInfos = reviewsDetailInfoMapper.selectPage(new Page<>(i, MIGRATE_PAGE_SIZE), null).getRecords();
            if(CollectionUtils.isNotEmpty(sourceReviewsDetailInfos)) {
                List<ReviewsDetailInfoSharding> targetReviewsDetailInfos = new ArrayList<>();
                for(ReviewsDetailInfo sourceReviewsDetailInfo : sourceReviewsDetailInfos) {
                    ReviewsDetailInfoSharding targetReviewsDetailInfo = new ReviewsDetailInfoSharding();
                    BeanUtils.copyProperties(targetReviewsDetailInfo, sourceReviewsDetailInfo);
                    targetReviewsDetailInfos.add(targetReviewsDetailInfo);
                }
                log.info("迁移reviews_detail_info, 迁移页码: {}, 迁移页大小: {}", i, targetReviewsDetailInfos.size());
                reviewsDetailInfoShardingService.saveBatch(targetReviewsDetailInfos);
            }
        }

        log.info("迁移reviews_info");
        for(long i = 1L; i <= MIGRATE_MAX_PAGE_NUM; i++) {
            List<ReviewsInfo> sourceReviewsInfos = reviewsInfoMapper.selectPage(new Page<>(i, MIGRATE_PAGE_SIZE), null).getRecords();
            if(CollectionUtils.isNotEmpty(sourceReviewsInfos)) {
                List<ReviewsInfoSharding> targetReviewsInfos = new ArrayList<>();
                for(ReviewsInfo sourceReviewsInfo : sourceReviewsInfos) {
                    ReviewsInfoSharding targetReviewsInfo = new ReviewsInfoSharding();
                    BeanUtils.copyProperties(targetReviewsInfo, sourceReviewsInfo);
                    targetReviewsInfos.add(targetReviewsInfo);
                }
                log.info("迁移reviews_info, 迁移页码: {}, 迁移页大小: {}", i, targetReviewsInfos.size());
                reviewsInfoShardingService.saveBatch(targetReviewsInfos);
            }
        }
    }
}
