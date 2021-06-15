package com.fundamentalconstant.core.state.pojo.exception;

public class NoCaseDefinedException extends RuntimeException {

    public NoCaseDefinedException() {
    }

    public NoCaseDefinedException(String message) {
        super(message);
    }

    public NoCaseDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
}
