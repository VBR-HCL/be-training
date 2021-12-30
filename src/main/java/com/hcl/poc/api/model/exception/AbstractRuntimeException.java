package com.hcl.poc.api.model.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public abstract class AbstractRuntimeException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRuntimeException.class);

    public AbstractRuntimeException(String message) {
        super(message);
    }

    public AbstractRuntimeException(String message, Exception e) {
        super(message);
        LOGGER.error(message, e);
    }

    public abstract HttpStatus getStatus();
}
