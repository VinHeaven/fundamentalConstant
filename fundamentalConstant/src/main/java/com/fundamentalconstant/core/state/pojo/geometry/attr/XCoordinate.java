package com.fundamentalconstant.core.state.pojo.geometry.attr;

import lombok.*;

import java.math.*;

@EqualsAndHashCode(callSuper = true)
public class XCoordinate extends CartesianCoordinate {

    public XCoordinate(BigDecimal value) {
        super(value);
    }

    public XCoordinate(int value) {
        super(value);
    }

    public XCoordinate(long value) {
        super(value);
    }

    public XCoordinate(double value) {
        super(value);
    }

    public XCoordinate(String value) {
        super(value);
    }

    public static XCoordinate of(BigDecimal value) {
        return new XCoordinate(value);
    }

    public static XCoordinate of(int value) {
        return new XCoordinate(value);
    }

    public static XCoordinate of(long value) {
        return new XCoordinate(value);
    }

    public static XCoordinate of(double value) {
        return new XCoordinate(value);
    }
}
