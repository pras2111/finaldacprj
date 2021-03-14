package com.shareBuddy.excp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException 
{
    public RecordNotFoundException(String exception) {
        super(exception);
    }
}