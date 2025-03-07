package com.rnr.app.controller;

import com.rnr.app.data.ErrorResponseDto;
import com.rnr.app.exceptions.NotFoundElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundElement.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundElement notFoundElement, WebRequest webRequest){
        return createErrorResponse(notFoundElement, HttpStatus.BAD_REQUEST, webRequest);
    }

    /**
     * Handling any other exception that is not covered.
     * @param exception exception
     * @param webRequest webRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest){
        return createErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    private <T extends Exception> ResponseEntity<ErrorResponseDto> createErrorResponse(T exception, HttpStatus httpStatus, WebRequest webRequest) {
        final ErrorResponseDto errorResponseDto = ErrorResponseDto
                .builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(httpStatus)
                .errorTime(LocalDateTime.now())
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, httpStatus);
    }
}
