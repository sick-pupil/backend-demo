package com.example.demo.controller;

import com.example.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.Frame;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @author lhy
 * @create 2023-11-10 10:11
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/log")
    public void log() {
        log.trace("a");
        log.info("b");
        log.warn("c");
        log.debug("d");
        log.error("e");
    }

    @GetMapping("/securityEncode")
    public void securityEncode() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodeStr = passwordEncoder.encode("123");
        log.info(encodeStr);
    }

    @GetMapping("/streamFilter")
    public void streamFilter() {
        List<String> intArr = new ArrayList<>();
        intArr.add("1");
        intArr.add("2");
        intArr.add("3");
        intArr.add("4");
        intArr.add("5");
        intArr.add("6");

        List<Integer> result = intArr.stream().map(Integer::valueOf).filter(n -> n % 2 == 0).collect(Collectors.toList());

        log.info(result.toString());
    }

    @GetMapping("/collectionUtilsUnion")
    public void collectionUtilsUnion() {
        List<Long> a = new ArrayList<>();
        a.add(1L);
        a.add(2L);
        List<Long> b = new ArrayList<>();
        b.add(3L);
        b.add(2L);
        log.info(CollectionUtils.union(a, b).toString());
    }

    @GetMapping("/collectionUtilsSubtract")
    public void collectionUtilsSubtract() {
        List<Long> a = new ArrayList<>();
        a.add(1L);
        a.add(2L);
        List<Long> b = new ArrayList<>();
        b.add(1L);
        b.add(2L);
        log.info(CollectionUtils.subtract(a, b).toString());
    }

    @GetMapping("/thread")
    public void thread() {
        new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
    }

    @GetMapping("/io")
    public void io() {
        try(InputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\ASUS\\Desktop\\doc-list"))) {
            log.info("");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/getJavaFileContext")
    public void getJavaFileContext() {
        String javaFilePath = System.getProperty("user.dir") + "/src/main/java/com/example/demo/controller/TestController.java";
        try {
            Files.lines(Paths.get(javaFilePath)).forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/javacv")
    public void javacv() {
        // 视频文件路径（例如：本地文件路径）
        String videoFilePath = "F:\\vedio\\电影\\start-085ch\\start-085ch.mp4";
        // 输出图片的保存路径和文件名
        String outputImagePath = "C:\\Users\\Administrator\\Desktop\\frame.png";

        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFilePath);
        try {
            grabber.start();
            // 获取第一个可用于图像处理的帧
            Frame frame = grabber.grabImage();
            if (frame != null) {
                // 使用 Java2DFrameConverter 转换 Frame 为 BufferedImage
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage bufferedImage = converter.convert(frame);
                // 写入本地文件，图片格式可以选择 "png"、"jpg" 等
                ImageIO.write(bufferedImage, "png", new File(outputImagePath));
                System.out.println("图片保存成功：" + outputImagePath);
            } else {
                System.out.println("没有获取到图像帧！");
            }
            grabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/unrar")
    public void unrar(@RequestParam("file") MultipartFile file) {
        String path = "C:\\Users\\Administrator\\Desktop\\";
        File tmpRarFile = null;
        try {
            tmpRarFile = File.createTempFile("tmp", ".rar");
            file.transferTo(tmpRarFile);
            try(RandomAccessFile randomAccessFile = new RandomAccessFile(tmpRarFile, "r");
                RandomAccessFileInStream randomAccessFileInStream = new RandomAccessFileInStream(randomAccessFile);
                IInArchive inArchive = SevenZip.openInArchive(null, randomAccessFileInStream)) {
                ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();
                for (ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
                    //是否为文件
                    if (!item.isFolder()) {
                        log.info(item.getPath());
                        ExtractOperationResult result = item.extractSlow(data -> {
                            OutputStream fileOutputStream = null;
                            try {
                                fileOutputStream = new FileOutputStream(path + "1.pdf", true);
                                IOUtils.write(data, fileOutputStream);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                IOUtils.closeQuietly(fileOutputStream);
                            }
                            return data.length;
                        });

                        if (result == ExtractOperationResult.OK) {
                            log.info("ok");
                        }
                    }
                }
                simpleInArchive.close();
            }
        } catch (IOException ex) {
            log.info(ex.getMessage());
        } finally {
            tmpRarFile.delete();
        }
    }

    @PostMapping("/unzip")
    public void unzip(@RequestParam("file") MultipartFile file) {
        String path = "C:\\Users\\Administrator\\Desktop\\";

        try {
            try(ZipArchiveInputStream is = new ZipArchiveInputStream(new BufferedInputStream(file.getInputStream()))) {
                ZipArchiveEntry entry;
                while((entry = is.getNextZipEntry()) != null) {
                    log.info("aaa");
                    log.info(entry.getName());
                    if(!entry.isDirectory()) {
                        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(path + "1.pdf", true));
                        IOUtils.copy(is, os);
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch (IOException ex) {
            log.info(ex.getMessage());
        }
    }
}
