package com.demo.testdatasource.enums;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/13 12:25
 */
public interface IErrorCode {
    /**
     * 错误码，统一错误为7位，前3位标识应用，后4位按先后顺序递增
     */
    int getCode();

    /**
     * 错误描述，相当于异常的message
     */
    String getDesc();

    /**
     * http状态码 200,403...
     */
    int getHttpStatus();
}
