package com.rnr.app.exceptions;

public class MarshallingException extends RuntimeException{
    public MarshallingException(String msg, Throwable t) {
        super(msg, t);
    }
}
