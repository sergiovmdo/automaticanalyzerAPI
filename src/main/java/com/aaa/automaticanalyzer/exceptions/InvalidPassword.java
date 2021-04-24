package com.aaa.automaticanalyzer.exceptions;

public class InvalidPassword extends Exception {
    public InvalidPassword(){
        super("Password is not correct");
    }
}
