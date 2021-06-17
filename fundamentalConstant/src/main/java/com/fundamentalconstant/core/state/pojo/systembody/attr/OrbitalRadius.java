package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import javax.measure.*;
import javax.measure.quantity.*;

import static tech.units.indriya.unit.Units.*;

@EqualsAndHashCode(callSuper = true)
public class OrbitalRadius extends Radius {

    public OrbitalRadius(@NonNull Quantity<Length> length) {
        super(length);
    }

    public OrbitalRadius(@NonNull DecimalNumber decimalNumber, @NonNull Unit<Length> lengthUnit) {
        super(decimalNumber, lengthUnit);
    }

    public OrbitalRadius(@NonNull String value, @NonNull Unit<Length> lengthUnit) {
        super(value, lengthUnit);
    }

    public OrbitalRadius(@NonNull String quantity) {
        super(quantity);
    }

    public static OrbitalRadius zero() {
        return new OrbitalRadius("0", METRE);
    }
}
