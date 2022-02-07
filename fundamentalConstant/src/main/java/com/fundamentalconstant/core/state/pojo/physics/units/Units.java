package com.fundamentalconstant.core.state.pojo.physics.units;

import lombok.experimental.*;

import javax.measure.*;
import javax.measure.quantity.Mass;
import javax.measure.quantity.*;

import static tech.units.indriya.unit.Units.*;

@UtilityClass
public class Units {

    public static final Unit<Length> KILOMETRE = MetricPrefix.KILO(METRE);
    public static final Unit<Speed> KILOMETRE_PER_SECOND = KILOMETRE.divide(SECOND).asType(Speed.class);
    public static final Unit<Mass> TON = MetricPrefix.KILO(KILOGRAM);
}
