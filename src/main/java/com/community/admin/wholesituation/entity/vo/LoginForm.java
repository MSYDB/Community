package com.community.admin.wholesituation.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Classname LoginForm
 * @Description 前台参数接受类（用户名密码）
 * @Date 2021/10/24 15:54
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
}
