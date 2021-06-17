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
public class Radian {
    private static final DecimalValueValidator validator = NONE;

    @NonNull
    @JsonValue
    private final Quantity<Angle> value;

    public Radian(@NonNull Quantity<Angle> radian) {
        this.value = normalize(radian, validator);
    }

    public Radian(@NonNull DecimalNumber decimalNumber, @NonNull Unit<Angle> radianUnit) {
        this.value = normalize(decimalNumber, radianUnit, validator);
    }

    public Radian(@NonNull String value, @NonNull Unit<Angle> radianUnit) {
        this.value = normalize(new DecimalNumber(value), radianUnit, validator);
    }

    public Radian(@NonNull String quantity) {
        this.value = normalize(QuantityHelper.createQuantity(quantity), validator);
    }

    public static Radian zero() {
        return new Radian("0", RADIAN);
    }

    public Quantity<Angle> getQuantity() {
        return value;
    }

    @Override
    public String toString() {
        return ObjectMapperInstance.write(value);
    }
}
