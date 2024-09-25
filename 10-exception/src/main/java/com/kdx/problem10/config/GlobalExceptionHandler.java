package com.kdx.problem10.config;

import com.kdx.problem10.exception.CustomException;
import com.kdx.problem10.exception.ErrorCode;
import com.kdx.problem10.exception.InputRestriction;
import com.kdx.problem10.response.ApiResponse;
import com.kdx.problem10.response.ApiResponseStatus;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ApiResponse<InputRestriction> handleCustomException(
            HttpServletResponse response , CustomException exception) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ApiResponse<>(
                new ApiResponseStatus(exception.getErrorCode(), exception.getMessage()),
                exception.getInputRestriction());
    }
}
