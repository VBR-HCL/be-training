package com.hcl.poc.api.model.exception;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class ExceptionResponse {
    private String timestamp;
    private Integer status;
    private String message;
    private String path;

    public ExceptionResponse(String timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public static ResponseEntity<ExceptionResponse> buildResponseEntity(AbstractRuntimeException e, String path) {
        return ResponseEntity.status(e.getStatus())
                .body(new ExceptionResponse(
                        LocalDate.now().toString(),
                        e.getStatus().value(),
                        e.getLocalizedMessage(),
                        path)
                );
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
