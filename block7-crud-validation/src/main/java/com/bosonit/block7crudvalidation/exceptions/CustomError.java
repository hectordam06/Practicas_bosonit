package com.bosonit.block7crudvalidation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@AllArgsConstructor
@Data
public class CustomError {
    private Date timestamp;
    private int HttpCode;
    private String mensaje;

    public CustomError() {

    }
}
