package com.aaa.automaticanalyzer.exceptions;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(){
        super("El usuario ya existe");
    }
}
