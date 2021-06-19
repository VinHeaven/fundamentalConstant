package com.fundamentalconstant.core.state.pojo.physics.units;

import tech.units.indriya.function.*;

import javax.measure.*;
import javax.measure.quantity.*;

import static tech.units.indriya.unit.Units.*;

public class Units {

    public static final Unit<Speed> KILOMETRE_PER_SECOND = METRE_PER_SECOND.multiply(RationalNumber.of(1000, 1))
            .asType(Speed.class);

    public static final Unit<Length> KILOMETRE = METRE.multiply(RationalNumber.of(1000, 1))
            .asType(Length.class);

    private Units() {
    }
}
