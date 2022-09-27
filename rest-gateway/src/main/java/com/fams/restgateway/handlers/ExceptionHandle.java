package com.fams.restgateway.handlers;

import com.fams.controller.exceptions.InvalidSignInException;
import com.fams.manager.dtos.response.ObjectWrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidSignInException.class})
    public ResponseEntity<Object> handleException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ObjectWrapperResponse.builder().message("Email or password invalid").build()
        );
    }
}
