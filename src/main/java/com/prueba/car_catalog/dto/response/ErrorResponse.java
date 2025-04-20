package com.prueba.car_catalog.dto.response;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String code;
    private String message;
    private String path;
    private Map<String, String> details;

    public ErrorResponse(HttpStatus status, String code, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.code = code;
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(HttpStatus status, String code, String message, String path, Map<String, String> details) {
        this(status, code, message, path);
        this.details = details;
    }
}
