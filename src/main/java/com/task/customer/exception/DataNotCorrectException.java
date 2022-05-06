package com.task.customer.exception;

public class DataNotCorrectException extends RuntimeException{
    public DataNotCorrectException(String message) {
        super(message);
    }

    public DataNotCorrectException(Throwable cause) {
        super(cause);
    }
}
