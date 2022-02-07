package com.fundamentalconstant.core.utils;

import lombok.experimental.*;
import tech.units.indriya.*;
import tech.units.indriya.format.*;
import tech.units.indriya.quantity.*;

import javax.measure.*;
import javax.measure.quantity.*;
import java.text.*;
import java.util.*;

@UtilityClass
public class QuantityHelper {

    private static NumberDelimiterQuantityFormat formatter = NumberDelimiterQuantityFormat.builder()
            .setNumberFormat(NumberFormat.getInstance(Locale.US)).setUnitFormat(SimpleUnitFormat.getInstance(SimpleUnitFormat.Flavor.ASCII)).build();

    public static Quantity<?> createQuantity(CharSequence csq) {
        return formatter.parse(csq);
    }

    public static Quantity<Dimensionless> createQuantityDimensionless(CharSequence csq) {
        return AbstractQuantity.parse(csq).asType(Dimensionless.class);
    }

    public static <T extends Quantity<T>> Quantity<T> createQuantity(CharSequence csq, Class<T> clazz) {
        return formatter.parse(csq).asType(clazz);
    }

    public static <Q extends Quantity<Q>> ComparableQuantity<Q> createQuantity(Number value, Unit<Q> unit, Quantity.Scale scale) {
        return Quantities.getQuantity(value, unit, scale);
    }

    public static <Q extends Quantity<Q>> ComparableQuantity<Q> createQuantity(Number value, Unit<Q> unit) {
        return Quantities.getQuantity(value, unit);
    }

    public static <Q extends Quantity<Q>> Quantity<Q> createQuantity(Number[] values, Unit<Q>[] units, Quantity.Scale scale) {
        return Quantities.getQuantity(values, units, scale);
    }

    @SafeVarargs
    public static <Q extends Quantity<Q>> Quantity<Q> createQuantity(Number[] values, Unit<Q>... units) {
        return Quantities.getQuantity(values, units);
    }
}
