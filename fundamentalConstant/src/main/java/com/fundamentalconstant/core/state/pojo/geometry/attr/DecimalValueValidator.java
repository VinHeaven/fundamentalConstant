package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fundamentalconstant.core.state.pojo.exception.*;

public class DecimalValueValidator {

    public static final DecimalValueValidator POSITIVE_OR_ZERO = new DecimalValueValidator() {
        @Override
        public boolean validate(DecimalNumber value) {
            return value.greaterOrEqualTo(new DecimalNumber(0));
        }
    };

    public static final DecimalValueValidator ZERO_TO_ONE = new DecimalValueValidator() {
        @Override
        public boolean validate(DecimalNumber value) {
            return value.isBetweenOrOn(new DecimalNumber(0), new DecimalNumber(1));
        }
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
