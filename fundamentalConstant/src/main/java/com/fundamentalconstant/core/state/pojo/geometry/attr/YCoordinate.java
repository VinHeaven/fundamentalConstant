package com.fundamentalconstant.core.state.pojo.geometry.attr;

import lombok.*;

import java.math.*;

@EqualsAndHashCode(callSuper = true)
public class YCoordinate extends CartesianCoordinate {

    public YCoordinate(BigInteger value) {
        super(value);
    }

    public YCoordinate(int value) {
        super(value);
    }

    public YCoordinate(long value) {
        super(value);
    }

    public YCoordinate(double value) {
        super(value);
    }

    public static YCoordinate of(BigInteger value) {
        return new YCoordinate(value);
    }

    public static YCoordinate of(int value) {
        return new YCoordinate(value);
    }

    public static YCoordinate of(long value) {
        return new YCoordinate(value);
    }

    public static YCoordinate of(double value) {
        return new YCoordinate(value);
    }
}
