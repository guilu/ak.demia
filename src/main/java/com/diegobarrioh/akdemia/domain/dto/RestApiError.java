package com.diegobarrioh.akdemia.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@ApiModel(value = "Error", description = "Object with data about an error")
public class RestApiError {

    @ApiModelProperty(value = "The timestamp when the response was sent", required = true, example = "1555572810405")
    private long timestamp = System.currentTimeMillis();
    @ApiModelProperty(value = "The Http status code", required = true)
    private HttpStatus httpStatus;
    @ApiModelProperty(value = "The error message", required = true)
    private String message;
    @ApiModelProperty(value = "The list of errors")
    private List<String> errors;

    public RestApiError(HttpStatus httpStatus, String message, List<String> errors) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public RestApiError(HttpStatus httpStatus, String message, String error) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
