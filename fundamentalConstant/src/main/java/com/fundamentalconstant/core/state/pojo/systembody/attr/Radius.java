package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import lombok.*;

import javax.measure.*;
import javax.measure.quantity.*;

import static tech.units.indriya.unit.Units.*;

@EqualsAndHashCode(callSuper = true)
public class Radius extends Distance {

    public Radius(@NonNull Quantity<Length> length) {
        super(length);
    }

    public Radius(@NonNull DecimalNumber decimalNumber, @NonNull Unit<Length> lengthUnit) {
        super(decimalNumber, lengthUnit);
    }

    public Radius(@NonNull String value, @NonNull Unit<Length> lengthUnit) {
        super(value, lengthUnit);
    }

    public Radius(@NonNull String quantity) {
        super(quantity);
    }

    public static Radius zero() {
        return new Radius("0", METRE);
    }
}
