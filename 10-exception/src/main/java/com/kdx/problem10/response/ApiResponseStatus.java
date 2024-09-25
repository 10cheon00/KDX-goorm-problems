package com.kdx.problem10.response;

import com.kdx.problem10.exception.ErrorCode;

public record ApiResponseStatus(Integer code, String message) {
    public ApiResponseStatus(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), message);
    }

    public ApiResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
