package com.kdx.problem10.exception;

public enum ErrorCode {
    OK(2000),
    ERROR(5000);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
