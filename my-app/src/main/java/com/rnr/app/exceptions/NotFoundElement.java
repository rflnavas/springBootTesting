package com.rnr.app.exceptions;

public class NotFoundElement extends RuntimeException{

    public NotFoundElement(String msg){
        super(msg);
    }
}
