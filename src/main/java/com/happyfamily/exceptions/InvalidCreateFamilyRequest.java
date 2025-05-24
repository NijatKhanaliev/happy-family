package com.happyfamily.exceptions;

public class InvalidCreateFamilyRequest extends RuntimeException{
    public InvalidCreateFamilyRequest(String msg){
        super(msg);
    }
}
