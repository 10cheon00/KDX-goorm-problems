package com.kdx.problem10.exception;

public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;
    private final InputRestriction inputRestriction;

    public CustomException(ErrorCode errorCode, String message, InputRestriction inputRestriction) {
        this.errorCode = errorCode;
        this.message = message;
        this.inputRestriction = inputRestriction;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public InputRestriction getInputRestriction() {
        return inputRestriction;
    }
}
