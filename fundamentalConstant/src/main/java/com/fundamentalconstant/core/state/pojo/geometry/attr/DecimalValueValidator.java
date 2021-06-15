package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fundamentalconstant.core.state.pojo.exception.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalNumber.*;

public class DecimalValueValidator {

    public static final DecimalValueValidator POSITIVE_OR_ZERO = new DecimalValueValidator() {
        @Override
        public boolean validate(DecimalNumber value) {
            return value.greaterOrEqualTo(ZERO);
        }
    };

    public static final DecimalValueValidator ZERO_TO_ONE = new DecimalValueValidator() {
        @Override
        public boolean validate(DecimalNumber value) {
            return value.isBetweenOrOn(ZERO, ONE);
        }
    };

    public static final DecimalValueValidator NONE = new DecimalValueValidator() {
    };

    public DecimalNumber cleanAndValidate(DecimalNumber value) {
        value = clean(value);
        ValueNotValidException.throwIfNotValid(validate(value), value);
        return value;
    }

    @SuppressWarnings("all")
    public boolean validate(DecimalNumber value) {
        return true;
    }

    public DecimalNumber clean(DecimalNumber value) {
        return value;
    }
}
