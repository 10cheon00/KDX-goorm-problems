package com.kdx.problem10.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kdx.problem10.exception.InputRestriction;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        ApiResponseStatus status,
        ApiResponseMetaData metaData,
        T result,
        Map<String, Object> data) {

    public ApiResponse(ApiResponseStatus status, ApiResponseMetaData metadata ,T result) {
        this(status, metadata, result, null);
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus, Object data) {
        this(apiResponseStatus, null, null, data);
    }

    public ApiResponse(ApiResponseStatus status, ApiResponseMetaData metaData, T result, Object data) {
        this(status, metaData, result, new HashMap<>());
        this.data.put(data.getClass().getSimpleName(), data);
    }
}
