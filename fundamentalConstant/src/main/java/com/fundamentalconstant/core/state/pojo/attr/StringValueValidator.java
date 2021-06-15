package com.fundamentalconstant.core.state.pojo.attr;

import com.fundamentalconstant.core.state.pojo.exception.*;

import static org.apache.commons.lang3.StringUtils.*;

public class StringValueValidator {

    public static final StringValueValidator NOT_BLANK = new StringValueValidator() {
        @Override
        protected boolean validate(String value) {
            return isNotBlank(value);
        }

        @Override
        protected String clean(String value) {
            return strip(value);
        }
    };

    public String cleanAndValidate(String value) {
        value = clean(value);
        ValueNotValidException.throwIfNotValid(validate(value), value);
        return value;
    }

    @SuppressWarnings("all")
    protected boolean validate(String value) {
        return true;
    }

    protected String clean(String value) {
        return value;
    }
}
