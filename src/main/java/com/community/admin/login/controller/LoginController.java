package com.community.admin.login.controller;

import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.login.service.LoginService;
import com.community.admin.util.JwtUtil;
import com.community.admin.util.RedisUtil;
import com.community.admin.util.VerCodeGenerateUtil;
import com.community.admin.wholesituation.entity.MessageParameter;
import com.community.admin.wholesituation.entity.SysLoginLog;
import com.community.admin.wholesituation.entity.SysUser;
import com.community.admin.wholesituation.entity.ToEmail;
import com.community.admin.wholesituation.entity.vo.LoginForm;
import com.community.admin.wholesituation.mapper.LogMapper;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Classname LoginController
 * @Description 登录
 * @Date 2021/10/12 18:16
 * @Created by thx
 */
@Validated
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private LogMapper logMapper;
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private JavaMailSender mailSender;


    @PostMapping("/loginByPassword")
    public ResultJson loginByPassword(@RequestBody @Valid LoginForm loginForm) throws Exception {
        ResultJson<SysUser> result = loginService.loginByPassword(loginForm.getUserName(), loginForm.getPassword());
        if (result.getData() != null) {
            SysUser user = result.getData();
            String token = JwtUtil.createToken(user.getId(), user.getUserName());
            user.setToken(token);
            redisUtil.setExpire(token, user, 500);
            SysLoginLog sysLoginLog = new SysLoginLog(user.getId(), InetAddress.getLocalHost().getHostAddress());
            logMapper.insert(sysLoginLog);
            return result;
        } else {
            return result;
        }
    }

    @PostMapping("/loginByPhone")
    public ResultJson loginByPhone(@NotBlank(message = "电话不能为空") String phone,
                                   @NotBlank(message = "验证码不能为空") String code) throws UnknownHostException {
        ResultJson<SysUser> result = loginService.loginByPhone(phone, code);
        if (result.getData() != null) {
            SysUser user = result.getData();
            String token = JwtUtil.createToken(user.getId(), user.getUserName());
            user.setToken(token);
            redisUtil.setExpire(token, user, 500);
            logMapper.insert(new SysLoginLog(user.getId(), InetAddress.getLocalHost().getHostAddress()));
            return result;
        } else {
            return result;
        }
    }

    /**
     * 获取短信验证码，可用于登录和注册，五分钟内有效
     */
    @RequestMapping("/getPhoneCode")
    public ResultJson getPhoneCode(String phone) {
        MessageParameter messageParameter = new MessageParameter();
        String verCode = VerCodeGenerateUtil.generateMessageVerCode();
        redisUtil.setExpire(phone, verCode, GlobalConstant.EXPIRE_TIME_SECOND);
        String[] code = new String[]{verCode, "" + GlobalConstant.EXPIRE_TIME_MINUTE};
        SmsSingleSender sender = new SmsSingleSender(messageParameter.getAppId(), messageParameter.getAppKey());
        SmsSingleSenderResult result = null;
        try {
            result = sender.sendWithParam("86", phone, messageParameter.getTemplateId(),
                    code, messageParameter.getSmsSign(), "", "");
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.result == 0) {
            return ResultJson.success(code[0]);
        } else {
            return ResultJson.failure(result.errMsg, result.sid);
        }
    }

    @RequestMapping("/register")
    public ResultJson register(@Valid SysUser user, @NotBlank(message = "验证码不能为空") String code) {
        return loginService.register(user, code);
    }

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 获取邮箱验证码
     *
     * @param toEmail
     * @return
     */
    @RequestMapping("/getEmailCode")
    public ResultJson getEmailCode(ToEmail toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail.getTos());
        message.setSubject("您本次的验证码是");
        String verCode = VerCodeGenerateUtil.generateEmailVerCode();
        message.setText("您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码" + GlobalConstant.EXPIRE_TIME_MINUTE + "分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");

        mailSender.send(message);
        for (String s : toEmail.getTos()) {
            redisUtil.setExpire(s, verCode, GlobalConstant.EXPIRE_TIME_SECOND);
        }
        return ResultJson.success("发送成功");
    }

    /**
     * 忘记密码
     * 1、判断电话是否存在
     * 2、发送短信验证码
     * 3、设置新密码
     */
    @RequestMapping("forgetPassword")
    public ResultJson forgetPassword(@NotBlank String phone, @NotBlank String code, @NotBlank String password) {
        return loginService.forgetPassword(phone, code, password);
    }

}
