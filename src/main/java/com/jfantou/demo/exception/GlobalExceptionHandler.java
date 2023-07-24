package com.jfantou.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleFilterNotFoundException(
            Exception exception, WebRequest request
    ){
        List<String> errorList = Arrays.asList(exception.getMessage());
        return buildErrorResponse(errorList,HttpStatus.INTERNAL_SERVER_ERROR, 1);
    }

    @ExceptionHandler({CourseNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleFilterNotFoundException(
            CourseNotFoundException exception
    ){

        List<String> errorList = Arrays.asList(exception.getMessage());
        return buildErrorResponse(errorList,HttpStatus.NOT_FOUND, 1);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders httpHeaders, HttpStatusCode statusCode, WebRequest request) {
        List<String> errorList = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ErrorResponse errorResponse = ErrorResponse.builder().messageError(errorList).status(HttpStatus.BAD_REQUEST.value()).timestamp(LocalDateTime.now()).countError(errorList.size()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            List<String> errorMessage,
            HttpStatus httpStatus, int countError
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                errorMessage,
                LocalDateTime.now(),
                countError
        );

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
