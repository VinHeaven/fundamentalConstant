package com.fundamentalconstant.core.state.pojo.geometry.attr;

import lombok.*;

import javax.measure.*;
import javax.measure.quantity.*;

@EqualsAndHashCode(callSuper = true)
public class XCoordinate extends CartesianCoordinate {

    public XCoordinate(@NonNull Quantity<Length> length) {
        super(length);
    }

    public XCoordinate(@NonNull DecimalNumber decimalNumber, @NonNull Unit<Length> lengthUnit) {
        super(decimalNumber, lengthUnit);
    }

    public XCoordinate(@NonNull String value, @NonNull Unit<Length> lengthUnit) {
        super(value, lengthUnit);
    }
}
