package com.example.demo.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.example.demo.config.EasyExcelImgUrlConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 头背景设置
@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, horizontalAlignment = HorizontalAlignmentEnum.CENTER, borderLeft = BorderStyleEnum.THIN, borderTop = BorderStyleEnum.THIN, borderRight = BorderStyleEnum.THIN, borderBottom = BorderStyleEnum.THIN)
//标题高度
@HeadRowHeight(20)
//内容高度
@ContentRowHeight(40)
//内容居中,左、上、右、下的边框显示
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER, borderLeft = BorderStyleEnum.THIN, borderTop = BorderStyleEnum.THIN, borderRight = BorderStyleEnum.THIN, borderBottom = BorderStyleEnum.THIN)
public class EasyExcelExportData2 {

    @ExcelProperty(value = "名称")
    @ColumnWidth(10)
    private String name;

    @ExcelProperty(value = "照片", converter = EasyExcelImgUrlConverter.class)
//    @ColumnWidth(10)
    private List<URL> images;

    @ExcelProperty(value = "年龄")
    @ColumnWidth(10)
    private Integer age;

}