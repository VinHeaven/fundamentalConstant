package com.fundamentalconstant.core.state.pojo.attr;

import com.fundamentalconstant.core.state.pojo.exception.*;

public abstract class StringValueValidator {

    String cleanAndValidate(String value) {
        value = clean(value);
        ValueNotValidException.throwIfNotValid(validate(value), value);
        return value;
    }

    @SuppressWarnings("all")
    boolean validate(String value) {
        return true;
    }

    String clean(String value) {
        return value;
    }
}
