package com.order.controller;

import com.common.utils.ResultUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 文件上传处理类
 * @date 2020/5/21
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/upload")
    public ResultUtils uploadFile(@RequestParam("image") MultipartFile file) {
        if(!file.isEmpty()) {
            try(InputStream inputStream = file.getInputStream()) {
                StringBuffer sb = request.getRequestURL();
                String fileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
                logger.info("rootPath is: " + rootPath.getPath());
                File imagePath = new File(rootPath.getParentFile().getParentFile().getParentFile().getAbsolutePath(), "uploadImages");
                Path path = Paths.get(imagePath.getAbsolutePath(), fileName);
                Files.copy(inputStream, path);
                return ResultUtils.success("", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResultUtils.error(500, "");
    }
}
