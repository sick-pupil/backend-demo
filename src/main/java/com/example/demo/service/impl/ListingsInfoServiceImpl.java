package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.ListingsInfo;
import com.example.demo.service.ListingsInfoService;
import com.example.demo.mapper.ListingsInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【listings_info】的数据库操作Service实现
* @createDate 2024-08-22 02:09:07
*/
@Service
public class ListingsInfoServiceImpl extends ServiceImpl<ListingsInfoMapper, ListingsInfo>
    implements ListingsInfoService{

}




