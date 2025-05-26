package com.example.demo.service;

import com.example.demo.bean.EasyExcelExportData;
import com.example.demo.bean.EasyExcelExportData2;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface EasyExcelService {

    void export(List<EasyExcelExportData> data);

    void export2(List<EasyExcelExportData2> data);
}
