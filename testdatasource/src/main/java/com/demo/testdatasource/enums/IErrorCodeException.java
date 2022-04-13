package com.demo.testdatasource.enums;

import com.alibaba.citrus.webx.BadRequestException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/4/13 12:25
 */
public interface IErrorCodeException extends IErrorCode{
    default void throwException() {
        throwException("");
    }

    default void throwException(String message) {
        if (StringUtils.isBlank(message)) {
            message = this.getDesc();
        }
        switch (this.getHttpStatus()) {
            case 400:// HttpStatus.BAD_REQUEST:
                throw new BadRequestException(message);
        }
    }
}
