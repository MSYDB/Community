package com.community.admin.interceptor;

import com.community.admin.anno.AuthString;
import com.community.admin.util.JwtUtil;
import com.community.admin.util.RedisUtil;
import com.community.admin.util.StringUtil;
import com.community.admin.wholesituation.entity.SysUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname LoginInterceptor
 * @Description 登陆拦截器
 * @Date 2021/10/12 18:04
 * @Created by thx
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        如果不是 controller 的方法，不拦截
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtil.isOrNotEmpty(token)) {
            return false;
        }

        String audience = JwtUtil.getAudience(token);
//        如果没有找到，说明没登录，进行拦截
        if (!JwtUtil.verifyToken(token, audience)) {
            return false;
        }
        SysUser user = (SysUser) redisUtil.get(token);
        if (user == null) {
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthString authString = handlerMethod.getMethodAnnotation(AuthString.class);
//        空路径不拦截
        if (authString == null) {
            return true;
        }
//       无对应权限，进行拦截
        if (!user.getAuthList().contains(authString.value())) {
            return false;
        }
        return true;
    }
}
