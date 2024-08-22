package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Calendar;
import com.example.demo.service.CalendarService;
import com.example.demo.mapper.CalendarMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【calendar】的数据库操作Service实现
* @createDate 2024-08-22 02:07:29
*/
@Service
public class CalendarServiceImpl extends ServiceImpl<CalendarMapper, Calendar>
    implements CalendarService{

}




