package com.fundamentalconstant.core.state.pojo.geometry.attr;

import lombok.*;

import javax.measure.*;
import javax.measure.quantity.*;

@EqualsAndHashCode(callSuper = true)
public class YCoordinate extends CartesianCoordinate {

    public YCoordinate(@NonNull Quantity<Length> length) {
        super(length);
    }

    public YCoordinate(@NonNull DecimalNumber decimalNumber, @NonNull Unit<Length> lengthUnit) {
        super(decimalNumber, lengthUnit);
    }

    public YCoordinate(@NonNull String value, @NonNull Unit<Length> lengthUnit) {
        super(value, lengthUnit);
    }
}
