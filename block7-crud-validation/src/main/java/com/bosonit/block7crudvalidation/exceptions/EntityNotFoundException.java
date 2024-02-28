package com.bosonit.block7crudvalidation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {
    private CustomError customError;

    public EntityNotFoundException(Date timestamp, int httpCode, String mensaje) {
        super(mensaje);

    }

    public CustomError getCustomError() {
        return customError;
    }
}
