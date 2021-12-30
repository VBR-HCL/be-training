package com.hcl.poc.api.webservice;

import com.hcl.poc.api.model.exception.AbstractRuntimeException;
import com.hcl.poc.api.model.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractRuntimeException.class)
    public ResponseEntity<ExceptionResponse> processUnauthorizedException(HttpServletRequest request, AbstractRuntimeException e) {
        return ExceptionResponse.buildResponseEntity(e, request.getServletPath());
    }
}
