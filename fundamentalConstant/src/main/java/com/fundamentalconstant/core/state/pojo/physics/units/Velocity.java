package com.fundamentalconstant.core.state.pojo.physics.units;

import com.fasterxml.jackson.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.utils.mapper.module.*;
import lombok.*;

import javax.measure.*;
import javax.measure.quantity.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalValueValidator.*;
import static com.fundamentalconstant.core.state.pojo.physics.QuantityUtils.*;
import static tech.units.indriya.unit.Units.*;

@EqualsAndHashCode
public class Velocity {

    private static final DecimalValueValidator validator = NONE;

    @NonNull
    @JsonValue
    private final Quantity<Speed> value;

    public Velocity(@NonNull Quantity<Speed> velocity) {
        this.value = normalize(velocity, validator);
    }

    public Velocity(@NonNull DecimalNumber decimalNumber, @NonNull Unit<Speed> velocityUnit) {
        this.value = normalize(decimalNumber, velocityUnit, validator);
    }

    public Velocity(@NonNull String value, @NonNull Unit<Speed> velocityUnit) {
        this.value = normalize(new DecimalNumber(value), velocityUnit, validator);
    }

    public Velocity(@NonNull String quantity) {
        this.value = normalize(QuantityHelper.createQuantity(quantity), validator);
    }

    public static Velocity zero() {
        return new Velocity("0", METRE_PER_SECOND);
    }

    public Quantity<Speed> getQuantity() {
        return value;
    }

    public boolean isNotZero() {
        return !isZero();
    }

    public boolean isZero() {
        return value.isEquivalentTo(QuantityHelper.createQuantity(0, METRE_PER_SECOND));
    }

    @Override
    public String toString() {
        return ObjectMapperInstance.write(value);
    }
}
