package com.demo.testdatasource.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/13 12:17
 */
@Getter
public enum BaseErrorCodes {
    /**
     * 成功响应枚举
     */
    SUCCESS(0, "成功", HttpStatus.OK),
    /**
     * 失败响应枚举
     */
    FAILED(1, "失败", HttpStatus.SERVICE_UNAVAILABLE),
    /**
     * 请求参数异常响应枚举
     */
    BAD_REQUEST(1000000, "请求参数异常", HttpStatus.BAD_REQUEST),
    /**
     * 用户验证失败响应枚举
     */
    UNAUTHORIZED(1000001, "用户验证失败", HttpStatus.UNAUTHORIZED),
    /**
     * 用户权限不足响应枚举
     */
    FORBIDDEN(1000002, "用户权限不足", HttpStatus.FORBIDDEN),
    /**
     * 服务器忙，稍后再试的响应枚举
     */
    SERVICE_UNAVAILABLE(1000003, "服务器忙，稍后再试", HttpStatus.SERVICE_UNAVAILABLE),
    /**
     * 不允许的请求方法响应枚举
     */
    METHOD_NOT_ALLOWED(1000004, "不允许的请求方法", HttpStatus.METHOD_NOT_ALLOWED),
    /**
     * 服务器内部错误响应枚举
     */
    INTERNAL_SERVER_ERROR(1000005, "服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * 外部接口调用异常
     */
    EXTERNAL_API_ERROR(1000006, "外部接口调用异常", HttpStatus.SERVICE_UNAVAILABLE),
    /**
     * 外部接口调用返回失败
     */
    EXTERNAL_API_FAIL(1000007, "外部接口调用返回失败", HttpStatus.SERVICE_UNAVAILABLE),
    /**
     * 外部接口调用返回的报文体异常
     */
    EXTERNAL_API_RESP_CONTENT_ERROR(1000008, "外部接口调用返回的报文体异常", HttpStatus.SERVICE_UNAVAILABLE),
    /**
     * 系统维护中
     */
    NOT_ACCEPTABLE(1000009, "系统维护中", HttpStatus.NOT_ACCEPTABLE),

    /**
     * 空指针的异常
     */
    NULL_POINTER_ERROR(1000010, "空指针的异常", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String desc;
    private final HttpStatus httpStatus;

    BaseErrorCodes(int code, String desc, HttpStatus httpStatus) {
        this.code = code;
        this.desc = desc;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getHttpStatus() {
        return this.httpStatus.value();
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", code, httpStatus, desc);
    }

}
