package com.fundamentalconstant.core.state.pojo.exception;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fundamentalconstant.core.*;

public class ValueNotValidException extends RuntimeException {

    private static final ObjectMapper om = ObjectMapperFactory.getMapper();

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
            try {
                throw new ValueNotValidException(String.format("Value was not valid. Value: (%s)", om.writeValueAsString(value)));
            } catch (JsonProcessingException e) {
                throw new ProjectException(e);
            }
        }
    }
}
