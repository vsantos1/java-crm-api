package com.vsantos1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID =  1L;

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
