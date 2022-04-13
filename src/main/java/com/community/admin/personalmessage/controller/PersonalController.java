package com.community.admin.personalmessage.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.personalmessage.service.PersonalService;
import com.community.admin.util.FileUtil;
import com.community.admin.wholesituation.entity.SysUser;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Classname PersonalController
 * @Description 个人中心 controller 层
 * @Date 2021/10/14 9:54
 * @Created by thx
 */
@RestController
@Validated
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "user")
public class PersonalController {
    @Resource
    private PersonalService personalService;

    /**
     * 修改个人信息
     */
    @SelfLog(module = "user", type = GlobalConstant.UPDATE, name = "修改个人信息")
    @AuthString("updatePersonalMessage")
    @PostMapping("updatePersonalMessage")
    public ResultJson updatePersonalMessage(@Valid SysUser user, @NotBlank String code) {
        return personalService.updatePersonalMessage(user, code);
    }


    /**
     * 修改密码
     */

    @AuthString("updatePassword")
    @SelfLog(module = "user", type = GlobalConstant.UPDATE, name = "修改密码")
    @RequestMapping("updatePassword")
    public ResultJson updatePassword(@NotBlank String oldPassword, @NotBlank String newPassword,
                                     @NotBlank String newPasswordAgain, HttpServletRequest request) {
        return personalService.updatePassword(oldPassword, newPassword, newPasswordAgain, request);
    }


    /**
     * 查看个人信息
     */
    @SelfLog(module = "user", type = GlobalConstant.SELECT, name = "查看个人信息")
    @AuthString("checkPersonalMessage")
    @RequestMapping("checkPersonalMessage")
    public ResultJson checkPersonalMessage(HttpServletRequest request) {
        return personalService.checkPersonalMessage(request);
    }

    /**
     * 上传头像
     *
     * @param request
     * @param photo
     * @return
     */
    @SelfLog(module = "user", type = GlobalConstant.INSERT, name = "上传头像")
    @AuthString("uploadMsg")
    @RequestMapping("uploadMsg")
    public ResultJson uploadMsg(HttpServletRequest request, @RequestParam("photo") MultipartFile photo) {
        return personalService.uploadMsg(request, photo);
    }

    /**
     * 根据路径下载文件
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    @SelfLog(module = "user", type = GlobalConstant.INSERT, name = "下载头像")
    @AuthString("downloadMsg")
    @RequestMapping("downloadByFilePath")
    public ResponseEntity<byte[]> download(String filePath) throws IOException {
        if (StringUtils.isEmpty(filePath)) {
            throw new RuntimeException("路径不可为空！");
        }
        /**
         * 获取磁盘路径
         */
        // 获取到文件相对路径
        // /filePrefix/20210626/20210626100834650_RFHASX.jpg => 20210626/20210626100834650_RFHASX.jpg
        String path = filePath.substring(filePath.indexOf(FileUtil.filePrefix) + FileUtil.filePrefix.length() - 1);
        // 磁盘根路径 + 相对路径  获取绝对路径
        // D:/qcby/20210626/20210626100834650_RFHASX.jpg
        String localPath = FileUtil.uploadLocalPath + path;
        File file = new File(localPath);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在！");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
                new String(file.getName().getBytes(StandardCharsets.UTF_8), "iso-8859-1"));
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 获取文件的字节数组 - 需要使用commons-io依赖包
        byte[] content = FileUtils.readFileToByteArray(file);
        // 返回下载的二进制内容
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }


}
