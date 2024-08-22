package com.example.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.bean.Calendar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.CalendarSharding;

/**
* @author Administrator
* @description 针对表【calendar】的数据库操作Mapper
* @createDate 2024-08-22 02:07:29
* @Entity com.example.demo.bean.Calendar
*/
@DS("sharding_readwrite_splitting_airbnb_db")
public interface CalendarShardingMapper extends BaseMapper<CalendarSharding> {

}




