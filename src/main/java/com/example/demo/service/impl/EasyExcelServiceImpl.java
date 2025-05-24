package com.example.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.demo.bean.EasyExcelExportData;
import com.example.demo.bean.EasyExcelExportData2;
import com.example.demo.config.EasyExcelCustomImageModifyStrategy;
import com.example.demo.service.EasyExcelService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EasyExcelServiceImpl implements EasyExcelService {

    @Override
    public void export(List<EasyExcelExportData> data) {
        EasyExcel.write("C:\\Users\\10045\\Desktop\\easyexcel-export.xlsx", EasyExcelExportData.class).sheet().doWrite(data);
    }

    @Override
    public void export2(List<EasyExcelExportData2> data) {
        Integer maxImgNum = data.stream().mapToInt(i -> {
            if(CollectionUtils.isEmpty(i.getImages())) {
                return 0;
            } else {
                return i.getImages().size();
            }
        }).max().orElse(0);

        EasyExcel.write("C:\\Users\\10045\\Desktop\\easyexcel-export2.xlsx", EasyExcelExportData2.class)
                .autoCloseStream(true)
                .registerWriteHandler(
                        // 设置每张图片的宽度为 60px，转换因子为 32
                        new EasyExcelCustomImageModifyStrategy(60, 32))
                // 使用图片处理策略
                .sheet("sheet")
                .doWrite(data);
    }
}
