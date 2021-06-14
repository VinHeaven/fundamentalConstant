package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fundamentalconstant.core.state.pojo.exception.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;

public abstract class DecimalValueValidator {

    DecimalNumber cleanAndValidate(DecimalNumber value) {
        value = clean(value);
        ValueNotValidException.throwIfNotValid(validate(value), value);
        return value;
    }

    @SuppressWarnings("all")
    boolean validate(DecimalNumber value) {
        return true;
    }

    DecimalNumber clean(DecimalNumber value) {
        return value;
    }
}
