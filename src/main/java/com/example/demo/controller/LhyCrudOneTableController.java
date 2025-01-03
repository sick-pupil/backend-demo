package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.bean.LhyCrudOneTable;
import com.example.demo.service.LhyCrudOneTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lhyCrudOneTableController")
public class LhyCrudOneTableController {

    @Autowired
    private LhyCrudOneTableService lhyCrudOneTableService;

    @PutMapping("/create")
    public void create(@RequestBody LhyCrudOneTable req) {
        lhyCrudOneTableService.save(req);
    }

    @GetMapping("/read")
    public List read(LhyCrudOneTable req) {
        LambdaQueryWrapper<LhyCrudOneTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(req.getField1()), LhyCrudOneTable::getField1, req.getField1());

        // 获取当前日期
        LocalDate today = req.getField2();
        // 获取当天的开始时间（00:00:00.000）
        LocalDateTime startOfDay = today.atTime(LocalTime.MIDNIGHT);
        long startOfDayMillis = startOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("当天的第一毫秒: " + startOfDayMillis);
        // 获取当天的结束时间（23:59:59.999）
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        long endOfDayMillis = endOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("当天的最后一毫秒: " + endOfDayMillis);

//        queryWrapper.between(ObjectUtils.isNotEmpty(req.getField2()), LhyCrudOneTable::getField2, startOfDay, endOfDay);
        queryWrapper.ge(ObjectUtils.isNotEmpty(req.getField2()), LhyCrudOneTable::getField2, startOfDay);
        queryWrapper.lt(ObjectUtils.isNotEmpty(req.getField2()), LhyCrudOneTable::getField2, endOfDay);
        return lhyCrudOneTableService.list(queryWrapper);
    }

    @PostMapping("/update")
    public void update(@RequestBody LhyCrudOneTable req) {
        lhyCrudOneTableService.updateById(req);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody LhyCrudOneTable req) {
        lhyCrudOneTableService.removeById(req);
    }
}
