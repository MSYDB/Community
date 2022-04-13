package com.community.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @Classname FileUtil
 * @Description 文件工具类
 * @Date 2021/10/11 17:28
 * @Created by thx
 */
@Slf4j
public class FileUtil {
    /**
     * 文件上传路径前缀
     */
    public static String filePrefix;
    /**
     * 本地磁盘目录
     */
    public static String uploadLocalPath;

    /**
     * @Title: uploadFile
     * @Description: 单文件上传到本地磁盘
     * @param: multipartFile
     * @return: java.lang.String
     * @throws:
     */
    public static String uploadFile(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        //生成文件名称，以免上传相同文件异常
        String fileName = getUploadFileName(multipartFile.getOriginalFilename());
        // 获取当前日期
        String dateDir = DateUtil.format(null, DateUtil.PATTERN_yyyyMMdd);
        // 如果是今天第一次上传，则生成日期文件夹
        File destFileDir = new File(uploadLocalPath + File.separator + dateDir);
        // 文件夹不存在时，创建文件夹
        if (!destFileDir.exists()) {
            destFileDir.mkdirs();
        }
        try {
            // 获取上传后文件对象
            File destFile = new File(destFileDir.getAbsoluteFile() + File.separator + fileName);
            // 上传文件到磁盘指定位置
            multipartFile.transferTo(destFile);
            log.info("文件【" + multipartFile.getOriginalFilename() + "】上传成功");
            // /filePrefix/ + 20210626 + / + 20210626093729817  +   _   +   HU5WMH  +   .jpg
            return filePrefix + dateDir + "/" + fileName;
        } catch (IOException e) {
            log.error("文件上传异常：" + multipartFile.getOriginalFilename(), e);
            return null;
        }
    }

    public static String moveFile(File file) {
        if (file == null) {
            return null;
        }
        //获取文件相对路径
        String fileName = getUploadFileName(file.getName());
        String dateDir = DateUtil.format(null, DateUtil.PATTERN_yyyyMMdd);
        File destFileDir = new File(uploadLocalPath + File.separator + dateDir);
        if (!destFileDir.exists()) {
            destFileDir.mkdirs();
        }
        File destFile = new File(destFileDir.getAbsoluteFile() + File.separator + fileName);
        file.renameTo(destFile);
        log.info("文件【" + file + "】上传成功");
        return filePrefix + "/" + dateDir + "/" + fileName;
    }

    /**
     * @Title: getUploadFilePath
     * @Description: 获取上传后的文件相对路径  --数据库存储该路径
     * @param: fileName
     * @return: java.lang.String
     * @throws:
     */
    public static String getUploadFileName(String fileName) {
        //20210626093729817  +   _   +   HU5WMH  +   .jpg
        return new StringBuilder()
                .append(DateUtil.format(null, DateUtil.PATTERN_yyyyMMddHHmmssSSS))
                .append("_").append(getRandomStrByNum(6))
                .append(getExtension(fileName))
                .toString();
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static void main(String[] args) {
//        System.out.println(getExtension("asdasdas.txt"));

        String str = "hb.......u66.txt";
        System.out.println(str.indexOf("7"));

        System.out.println(str.indexOf(".")); // 获取字符第一次出现的下标

        System.out.println(str.charAt(5));

        System.out.println(str.substring(str.lastIndexOf("."))); // 截取字符串,获取扩展名

        String str1 = getRandomStrByNum(6);
        System.out.println(str1);

    }

    public static String CHAR_STR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * @Title: getRandomStrByNum
     * @Description: 获取不同位数的随机字符串
     * @Author: lxt
     * @param: factor
     * @return: java.lang.String
     * @throws:
     */
    public static String getRandomStrByNum(int factor) {
        // 拼接字符串
        StringBuilder sb = new StringBuilder();
        // java随机数对象
        Random random = new Random();
        for (int i = 0; i < factor; i++) {
            int index = random.nextInt(36);
            System.out.println("========>:" + index);
            char c = CHAR_STR.charAt(index);
            System.out.println("========>:" + c);
            sb.append(c);
        }
        return sb.toString();
    }
}
