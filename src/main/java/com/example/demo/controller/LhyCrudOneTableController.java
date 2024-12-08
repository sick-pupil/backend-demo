package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.bean.LhyCrudOneTable;
import com.example.demo.service.LhyCrudOneTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        queryWrapper.eq(ObjectUtils.isNotEmpty(req.getField2()), LhyCrudOneTable::getField1, req.getField1());
        queryWrapper.eq(StringUtils.isNotBlank(req.getField1()), LhyCrudOneTable::getField2, req.getField2());
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
