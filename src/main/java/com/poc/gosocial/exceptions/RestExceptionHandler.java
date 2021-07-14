package com.poc.gosocial.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String error = "'"+ex.getParameterName()+"'" + " parameter is not present";

        log.info("[EXCEPTION] "+ex+" for {} {}",servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String error = "Malformed JSON request";

        log.info("[EXCEPTION] " + ex + " for {} {}",servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

}
