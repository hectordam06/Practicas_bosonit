package com.bosonit.crudrelaciones.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    CustomError error = new CustomError();
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException e){
        error.setTimestamp(new Date());
        error.setHttpCode(404);
        error.setMensaje(e.getMessage());
        return ResponseEntity.status(error.getHttpCode()).body(error);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntityException(UnprocessableEntityException e){
        error.setTimestamp(new Date());
        error.setHttpCode(422);
        error.setMensaje(e.getMessage());
        return ResponseEntity.status(error.getHttpCode()).body(error);
    }

}
