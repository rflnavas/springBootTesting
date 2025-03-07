package com.rnr.app.exceptions;

public class NotFoundResource extends RuntimeException{

    public NotFoundResource(String msg) {
        super(msg);
    }
}
