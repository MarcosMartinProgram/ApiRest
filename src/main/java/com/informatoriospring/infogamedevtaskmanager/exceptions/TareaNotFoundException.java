package com.informatoriospring.infogamedevtaskmanager.exceptions;

public class TareaNotFoundException extends RuntimeException{
    public TareaNotFoundException(String message) {
        super(message);
    }
}
