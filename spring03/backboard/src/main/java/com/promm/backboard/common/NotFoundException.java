package com.promm.backboard.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "entity isn't found")
public class NotFoundException extends RuntimeException{
    @SuppressWarnings("unused")
    private static final long SerialVersionUID = 1L;

    public NotFoundException(String message){
        super(message); // RuntimeException에서 처리!
    }
}
