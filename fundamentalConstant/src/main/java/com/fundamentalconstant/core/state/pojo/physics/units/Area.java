package com.fundamentalconstant.core.state.pojo.physics.units;

import com.fasterxml.jackson.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.utils.*;
import com.fundamentalconstant.core.utils.mapper.*;
import lombok.*;

import javax.measure.*;
import java.math.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalValueValidator.*;
import static com.fundamentalconstant.core.state.pojo.physics.QuantityUtils.*;
import static tech.units.indriya.unit.Units.*;

@Data
public class Area {

    private static final DecimalValueValidator validator = POSITIVE_OR_ZERO;

    @NonNull
    @JsonValue
    private final Quantity<javax.measure.quantity.Area> value;

    public Area(@NonNull Quantity<javax.measure.quantity.Area> value) {
        this.value = normalize(value, validator);
    }

    public Area(@NonNull DecimalNumber decimalNumber, @NonNull Unit<javax.measure.quantity.Area> areaUnit) {
        this.value = normalize(decimalNumber, areaUnit, validator);
    }

    public Area(@NonNull String value, @NonNull Unit<javax.measure.quantity.Area> areaUnit) {
        this.value = normalize(new DecimalNumber(value), areaUnit, validator);
    }

    public Area(@NonNull String quantity) {
        this.value = normalize(QuantityHelper.createQuantity(quantity), validator);
    }

    public static Area zero() {
        return new Area("0", SQUARE_METRE);
    }

    public boolean isNotZero() {
        return !isZero();
    }

    public boolean isZero() {
        return value.isEquivalentTo(QuantityHelper.createQuantity(0, SQUARE_METRE));
    }

    public Quantity<javax.measure.quantity.Area> getQuantity() {
        return value;
    }

    public BigDecimal getNormalizedValue() {
        return new BigDecimal(value.to(SQUARE_METRE).getValue().toString());
    }

    public Quantity<javax.measure.quantity.Area> add(Quantity<javax.measure.quantity.Area> addend) {
        return value.add(addend);
    }

    public Quantity<javax.measure.quantity.Area> subtract(Quantity<javax.measure.quantity.Area> subtrahend) {
        return value.subtract(subtrahend);
    }

    public Quantity<?> divide(Quantity<?> divisor) {
        return value.divide(divisor);
    }

    public Quantity<javax.measure.quantity.Area> divide(Number divisor) {
        return value.divide(divisor);
    }

    public Quantity<?> multiply(Quantity<?> multiplicand) {
        return value.multiply(multiplicand);
    }

    public Quantity<javax.measure.quantity.Area> multiply(Number multiplicand) {
        return value.multiply(multiplicand);
    }

    public Quantity<javax.measure.quantity.Area> to(Unit<javax.measure.quantity.Area> unit) {
        return value.to(unit);
    }

    public Unit<javax.measure.quantity.Area> getUnit() {
        return value.getUnit();
    }

    public Quantity<javax.measure.quantity.Area> toSystemUnit() {
        return value.toSystemUnit();
    }

    @Override
    public String toString() {
        return ObjectMapperInstance.write(value);
    }
}
