package com.kdx.problem10.response;

import com.kdx.problem10.exception.ErrorCode;

import java.util.Arrays;
import java.util.List;

public class ResponseGenerator {
    public static <T> ApiResponse<List<T>> makeResponse(T result) {
        List<T> results = Arrays.asList(result);
        return makeResponse(results);
    }

    public static <T> ApiResponse<List<T>> makeResponse(List<T> results) {
        return new ApiResponse<>(
                new ApiResponseStatus(ErrorCode.OK, "OK"),
                new ApiResponseMetaData(results.size()),
                results
        );
    }
}
