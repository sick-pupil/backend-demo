package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Calendar;
import com.example.demo.bean.CalendarSharding;
import com.example.demo.mapper.CalendarShardingMapper;
import com.example.demo.service.CalendarService;
import com.example.demo.mapper.CalendarMapper;
import com.example.demo.service.CalendarShardingService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【calendar】的数据库操作Service实现
* @createDate 2024-08-22 02:07:29
*/
@DS("sharding_readwrite_splitting_airbnb_db")
@Service
public class CalendarShardingServiceImpl extends ServiceImpl<CalendarShardingMapper, CalendarSharding>
    implements CalendarShardingService {

}




