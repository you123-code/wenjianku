package com.demo.testdatasource.utils;

import cn.hutool.json.JSONUtil;

import com.demo.testdatasource.dto.Pagination;
import com.demo.testdatasource.enums.BaseErrorCodes;
import com.demo.testdatasource.enums.IErrorCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/13 12:14
 */
@Getter
public class ResponseResult<T> implements Serializable {
    private String respCode;
    private String respMsg;
    private T data;

    private static final HashSet<Class<?>> baseTypes = new HashSet();
    private static final long serialVersionUID = 1L;

//    @Autowired
//    public ResponseResult(HttpServletResponse response) {
//        baseTypes.add(Short.class);
//        baseTypes.add(Integer.class);
//        baseTypes.add(Long.class);
//        baseTypes.add(Float.class);
//        baseTypes.add(Double.class);
//        baseTypes.add(String.class);
//        baseTypes.add(Character.class);
//        baseTypes.add(Byte.class);
//        baseTypes.add(Boolean.class);
//    }

    private ResponseResult(String respCode, String respMsg, T data) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.data = data;
    }

    public ResponseResult() {

    }

    public static <T> ResponseResult<T> ok() {
        return restResult(null,
                BaseErrorCodes.SUCCESS.getCode(),
                BaseErrorCodes.SUCCESS.getDesc());
    }

    public static <T> ResponseResult<T> error() {
        return restResult(null,
                BaseErrorCodes.FAILED.getCode(),
                BaseErrorCodes.FAILED.getDesc());
    }

    public static <T> ResponseResult<T> ok(T data) {
        return restResult(data,
                BaseErrorCodes.SUCCESS.getCode(),
                BaseErrorCodes.SUCCESS.getDesc());
    }

    public static <T> ResponseResult<Pagination<T>> pagination(List<T> list, int page, int pageSize, int total) {
        return restResult(new Pagination<T>(list, page,pageSize, total),
                BaseErrorCodes.SUCCESS.getCode(),
                BaseErrorCodes.SUCCESS.getDesc());
    }

    public static <T> ResponseResult<T> error(IErrorCode code, T data) {
        return error(code, code.getDesc(),data);
    }

    public static <T> ResponseResult<T> error(IErrorCode code,List<String> errorList) {
        String data = String.join(";", errorList);

        return (ResponseResult<T>) error(code, data, JSONUtil.createObj().set("nonce",String.valueOf(System.currentTimeMillis())));
    }

    public static <T> ResponseResult<T> error(IErrorCode code, String message,T data) {
        if (BaseErrorCodes.SUCCESS.equals(code)) {
            throw new IllegalArgumentException("ErrorCodes 不能为 SUCCESS");
        }


        return restResult(data,
                code.getCode(),
                message);
    }

//    public static <T> ResponseResult<T> error(IErrorCode code, List<String> messageList,T data) {
//        if (BaseErrorCodes.SUCCESS.equals(code)) {
//            throw new IllegalArgumentException("ErrorCodes 不能为 SUCCESS");
//        }
//
//        String message = String.join(";",messageList);
//        return restResult(null,
//                code.getCode(),
//                message);
//    }

    private static <T> ResponseResult<T> restResult(T data, int code, String message) {
//        Class<?> type = null;
//        if (data != null) {
//            if (data instanceof Pagination) {
//                List<T> list = ((Pagination<T>) data).getList();
//                if (list != null && !list.isEmpty()) {
//                    type = list.get(0).getClass();
//                }
//            } else if (data instanceof List) {
//                List<T> list = (List<T>) data;
//                if (!list.isEmpty()) {
//                    type = list.get(0).getClass();
//                }
//            } else {
//                type = data.getClass();
//            }
//        }
//        if (type != null && !type.getTypeName().endsWith("VO") && !baseTypes.contains(type)) {
//            throw new InternalServerErrorException("响应信息必须为VO或基本类型");
//        }
        return new ResponseResult<T>(String.valueOf(code), message, data);
    }
}
