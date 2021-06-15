package com.fundamentalconstant.core.state.pojo.physics;

import com.fundamentalconstant.core.state.pojo.geometry.attr.*;

public class AU {

    private static final DecimalNumber FACTOR = new DecimalNumber(149_597_870_700L);

    private final DecimalNumber value;

    public AU(DecimalNumber value) {
        this.value = value.multiply(FACTOR);
    }

    public AU(double value) {
        this.value = new DecimalNumber(value).multiply(FACTOR);
    }

    public AU(String value) {
        this.value = new DecimalNumber(value).multiply(FACTOR);
    }

    public DecimalNumber getValue() {
        return value;
    }
}
