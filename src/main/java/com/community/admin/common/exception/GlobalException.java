package com.community.admin.common.exception;


import com.community.admin.common.web.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @Classname GlobalException
 * @Description 全局异常处理类
 * @Date 2021/10/10 14:28
 * @Created by thx
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    /**
     * 通用异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResultJson labRunException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultJson.failure("系统异常：" + e.getCause().getMessage());
    }

    /**
     * 实体接收参数校验  post   MethodArgumentNotValidException，get BindException
     * 单个接收参数 ConstraintViolationException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class})
    public ResultJson handleValidException(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            return wrapperBindingResult(me.getBindingResult());
        }
        if (e instanceof BindException) {
            BindException be = (BindException) e;
            return wrapperBindingResult(be.getBindingResult());
        }
        if (e instanceof ConstraintViolationException) {
            return wrapperConstraintViolationExceptionResult((ConstraintViolationException) e);
        }
        return ResultJson.failure("参数错误");
    }

    private ResultJson wrapperConstraintViolationExceptionResult(ConstraintViolationException e) {
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return ResultJson.failure("参数错误");
        }
        for (ConstraintViolation<?> item : constraintViolations) {
            String propertyPath = item.getPropertyPath().toString();
            msg.append(", ").append(propertyPath.split("\\.")[1]).append(": ").append(item.getMessage());
        }

        return ResultJson.failure(msg.substring(2));
    }

    private ResultJson wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (CollectionUtils.isEmpty(allErrors)) {
            return ResultJson.failure("参数错误");
        }
        for (ObjectError error : allErrors) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }

        return ResultJson.failure(msg.substring(2));
    }


}
