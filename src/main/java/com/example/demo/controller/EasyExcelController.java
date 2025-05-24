package com.example.demo.controller;

import com.example.demo.bean.EasyExcelExportData;
import com.example.demo.bean.EasyExcelExportData2;
import com.example.demo.bean.UserInfo;
import com.example.demo.service.EasyExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/easyExcel")
@RequiredArgsConstructor
public class EasyExcelController {

    private final EasyExcelService easyExcelService;

    @GetMapping("/export")
    public void export() {
        try {
            List<EasyExcelExportData> data = new ArrayList<>();
            data.add(EasyExcelExportData.builder()
                    .name("米大傻")
                    .image(new URL("https://img-blog.csdnimg.cn/direct/c11088e1790049a5b84a0fda21a271b1.png"))
                    .age(18)
                    .build()
            );
            data.add(EasyExcelExportData.builder()
                    .name("曹大力")
                    .image(new URL("https://img-blog.csdnimg.cn/direct/bef2fdeffa644fb4aa6231d485ddaaac.png"))
                    .age(17)
                    .build()
            );
            data.add(EasyExcelExportData.builder()
                    .name("张大仙")
                    .image(new URL("https://img-blog.csdnimg.cn/direct/e264c110314d4ec49a7c79c51732f5f7.png"))
                    .age(18)
                    .build()
            );
            easyExcelService.export(data);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

    }

    @GetMapping("/export2")
    public void export2() {
        try {
            List<URL> imgList = new ArrayList<>();
            imgList.add(new URL(
                    "https://img-blog.csdnimg.cn/direct/c11088e1790049a5b84a0fda21a271b1.png"));
            imgList.add(new URL(
                    "https://img-blog.csdnimg.cn/direct/bef2fdeffa644fb4aa6231d485ddaaac.png"));
            imgList.add(new URL(
                    "https://img-blog.csdnimg.cn/direct/e264c110314d4ec49a7c79c51732f5f7.png"));

            List<EasyExcelExportData2> data = new ArrayList<>();
            data.add(EasyExcelExportData2.builder()
                    .name("米大傻")
                    .images(imgList)
                    .age(18)
                    .build()
            );
            data.add(EasyExcelExportData2.builder()
                    .name("曹大力")
                    .images(null)
                    .age(17)
                    .build()
            );
            data.add(EasyExcelExportData2.builder()
                    .name("张大仙")
                    .images(imgList)
                    .age(18)
                    .build()
            );
            easyExcelService.export2(data);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

    }
}
