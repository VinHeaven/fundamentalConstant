package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fundamentalconstant.core.state.pojo.exception.*;

public abstract class IntegerValueValidator {

    IntegerNumber cleanAndValidate(IntegerNumber value) {
        value = clean(value);
        ValueNotValidException.throwIfNotValid(validate(value), value);
        return value;
    }

    @SuppressWarnings("all")
    boolean validate(IntegerNumber value) {
        return true;
    }

    IntegerNumber clean(IntegerNumber value) {
        return value;
    }
}
