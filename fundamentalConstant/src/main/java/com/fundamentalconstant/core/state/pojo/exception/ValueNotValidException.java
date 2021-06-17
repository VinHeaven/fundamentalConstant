package com.fundamentalconstant.core.state.pojo.exception;

import com.fundamentalconstant.core.utils.mapper.module.*;

public class ValueNotValidException extends RuntimeException {

    public ValueNotValidException() {
    }

    public ValueNotValidException(String message) {
        super(message);
    }

    public ValueNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void throwIfNotValid(boolean valid, Object value) {
        if (!valid) {
            throw new ValueNotValidException(String.format("Value was not valid. Value: (%s)", ObjectMapperInstance.write(value)));
        }
    }
}
