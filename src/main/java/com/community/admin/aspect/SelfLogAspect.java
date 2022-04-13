package com.community.admin.aspect;

import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.util.RedisUtil;
import com.community.admin.wholesituation.entity.SysOperateLog;
import com.community.admin.wholesituation.entity.SysUser;
import com.community.admin.wholesituation.mapper.OperateLogMapper;
import com.community.admin.wholesituation.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Classname SelfLogAspect
 * @Description 切面
 * @Date 2021/10/10 14:48
 * @Created by thx
 */
@Aspect
@Component
@Slf4j
public class SelfLogAspect {
    @Resource
    private HttpServletRequest request;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private LogService logService;

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.community.admin.anno.SelfLog)")
    public void selfLogPointCut() {

    }

    /**
     * 切入
     */
    @Around("selfLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        String token = request.getHeader(GlobalConstant.TOKEN);
        SysUser user = (SysUser) redisUtil.get(token);
        if (user == null) {
            throw new Exception("用户未登录");
        }
        //获取注解信息
        SelfLog selfLog = method.getAnnotation(SelfLog.class);
        SysOperateLog operateLog = new SysOperateLog(user.getId(), selfLog.module(), selfLog.type(),
                selfLog.name());

        operateLogMapper.insert(operateLog);

        String nameValue = selfLog.name();
        String name = null;

        //获取切入点方法参数
        Object[] objects = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if (Objects.equals(nameValue, paramNames[i]) && objects[i] != null) {
                name = objects[i].toString();
            }
        }
        /**
         *  这是目标方法执行的代码
         */
        Object r = joinPoint.proceed();
        return r;
    }


}
