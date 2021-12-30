package com.hcl.poc.api.model.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractRuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
