package com.task.customer.exception;

public class FieldCorrectException extends RuntimeException{
    public FieldCorrectException(String message) {
        super(message);
    }

    public FieldCorrectException(Throwable cause) {
        super(cause);
    }
}
