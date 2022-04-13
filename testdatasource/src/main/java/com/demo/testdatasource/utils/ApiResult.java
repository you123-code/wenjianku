package com.demo.testdatasource.utils;

import com.demo.testdatasource.dto.Pagination;
import com.demo.testdatasource.enums.BaseErrorCodes;
import com.demo.testdatasource.enums.IErrorCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/13 17:10
 */
@Getter
public class ApiResult<T> implements Serializable {
    private int code;

    private String message;

    private T data;

    private long time;

    private static HttpServletResponse response;

    private final static HashSet<Class<?>> baseTypes = new HashSet<>();

    private static final long serialVersionUID = 1L;

    @Autowired
    public ApiResult(HttpServletResponse response) {
        ApiResult.response = response;
        baseTypes.add(Short.class);
        baseTypes.add(Integer.class);
        baseTypes.add(Long.class);
        baseTypes.add(Float.class);
        baseTypes.add(Double.class);
        baseTypes.add(String.class);
        baseTypes.add(Character.class);
        baseTypes.add(Byte.class);
        baseTypes.add(Boolean.class);
    }

    private ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public ApiResult() {
    }

    public static <T> ApiResult<T> ok() {
        return restResult(null,
                BaseErrorCodes.SUCCESS.getCode(),
                BaseErrorCodes.SUCCESS.getHttpStatus(),
                BaseErrorCodes.SUCCESS.getDesc());
    }

    public static <T> ApiResult<T> ok(T data) {
        return restResult(data,
                BaseErrorCodes.SUCCESS.getCode(),
                BaseErrorCodes.SUCCESS.getHttpStatus(),
                BaseErrorCodes.SUCCESS.getDesc());
    }

    public static <T> ApiResult<Pagination<T>> pagination(List<T> list, int page, int pageSize, int total) {
        return restResult(new Pagination<T>(list, page,pageSize, total),
                BaseErrorCodes.SUCCESS.getCode(),
                BaseErrorCodes.SUCCESS.getHttpStatus(),
                BaseErrorCodes.SUCCESS.getDesc());
    }

    public static <T> ApiResult<T> error(IErrorCode code) {
        return error(code, code.getDesc());
    }

    public static <T> ApiResult<T> error(IErrorCode code, String message) {
        if (BaseErrorCodes.SUCCESS.equals(code)) {
            throw new IllegalArgumentException("ErrorCodes 不能为 SUCCESS");
        }
        return restResult(null,
                code.getCode(),
                code.getHttpStatus(),
                message);
    }

    private static <T> ApiResult<T> restResult(T data, int code, int httpStatus, String message) {
        Class<?> type = null;
        if (data != null) {
            if (data instanceof Pagination) {
                List<T> list = ((Pagination<T>) data).getList();
                if (list != null && !list.isEmpty()) {
                    type = list.get(0).getClass();
                }
            } else if (data instanceof List) {
                List<T> list = (List<T>) data;
                if (!list.isEmpty()) {
                    type = list.get(0).getClass();
                }
            } else {
                type = data.getClass();
            }
        }
        //if (type != null && !type.getTypeName().endsWith("VO") && !baseTypes.contains(type)) {
        //    throw new exce("响应信息必须为VO或基本类型");
        //}
        response.setStatus(httpStatus);
        return new ApiResult<T>(code, message, data);
    }
}
