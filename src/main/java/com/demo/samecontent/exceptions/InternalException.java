package com.demo.samecontent.exceptions;

public class InternalException extends RuntimeException{
    public InternalException(String message, Throwable cause){
        super(message, cause);
    }
}
