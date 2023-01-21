package com.plantdiary.enterprise.exceptions;

public class InvalidCredentialsException extends IllegalArgumentException {
    public InvalidCredentialsException(String s) {
        super("The credentials you entered were incorrect "+ s);
    }
}
