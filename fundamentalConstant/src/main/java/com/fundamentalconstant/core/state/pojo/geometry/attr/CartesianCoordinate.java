package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fasterxml.jackson.annotation.*;
import com.fundamentalconstant.core.utils.*;
import com.fundamentalconstant.core.utils.mapper.*;
import lombok.*;

import javax.measure.*;
import javax.measure.quantity.*;
import java.math.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalValueValidator.*;
import static com.fundamentalconstant.core.state.pojo.physics.QuantityUtils.*;
import static tech.units.indriya.unit.Units.*;

@EqualsAndHashCode
public class CartesianCoordinate {

    private static final DecimalValueValidator validator = NONE;

    @NonNull
    @JsonValue
    private final Quantity<Length> value;

    public CartesianCoordinate(@NonNull Quantity<Length> length) {
        this.value = normalize(length, validator);
    }

    public CartesianCoordinate(@NonNull DecimalNumber decimalNumber, @NonNull Unit<Length> lengthUnit) {
        this.value = normalize(decimalNumber, lengthUnit, validator);
    }

    public CartesianCoordinate(@NonNull String value, @NonNull Unit<Length> lengthUnit) {
        this.value = normalize(new DecimalNumber(value), lengthUnit, validator);
    }

    public static CartesianCoordinate zero() {
        return new CartesianCoordinate("0", METRE);
    }

    public boolean isNotZero() {
        return !isZero();
    }

    public boolean isZero() {
        return value.isEquivalentTo(QuantityHelper.createQuantity(0, METRE));
    }

    public Quantity<Length> getQuantity() {
        return value;
    }

    public BigDecimal getNormalizedValue() {
        return new BigDecimal(value.to(METRE).getValue().toString());
    }

    public Quantity<Length> add(Quantity<Length> addend) {
        return value.add(addend);
    }

    public Quantity<Length> subtract(Quantity<Length> subtrahend) {
        return value.subtract(subtrahend);
    }

    public Quantity<?> divide(Quantity<?> divisor) {
        return value.divide(divisor);
    }

    public Quantity<Length> divide(Number divisor) {
        return value.divide(divisor);
    }

    public Quantity<?> multiply(Quantity<?> multiplicand) {
        return value.multiply(multiplicand);
    }

    public Quantity<Length> multiply(Number multiplicand) {
        return value.multiply(multiplicand);
    }

    public Quantity<Length> to(Unit<Length> unit) {
        return value.to(unit);
    }

    public Unit<Length> getUnit() {
        return value.getUnit();
    }

    public Quantity<Length> toSystemUnit() {
        return value.toSystemUnit();
    }

    @Override
    public String toString() {
        return ObjectMapperInstance.write(value);
    }
}
