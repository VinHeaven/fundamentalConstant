package com.fundamentalconstant.core.state.pojo.physics.units;

import com.fasterxml.jackson.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.utils.*;
import com.fundamentalconstant.core.utils.mapper.*;
import lombok.*;

import javax.measure.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalValueValidator.*;
import static com.fundamentalconstant.core.state.pojo.physics.QuantityUtils.*;
import static tech.units.indriya.unit.Units.*;

@EqualsAndHashCode
public class Mass {

    private static final DecimalValueValidator validator = POSITIVE_OR_ZERO;

    @NonNull
    @JsonValue
    private final Quantity<javax.measure.quantity.Mass> value;

    public Mass(@NonNull Quantity<javax.measure.quantity.Mass> mass) {
        this.value = normalize(mass, validator);
    }

    public Mass(@NonNull DecimalNumber decimalNumber, @NonNull Unit<javax.measure.quantity.Mass> massUnit) {
        this.value = normalize(decimalNumber, massUnit, validator);
    }

    public Mass(@NonNull String value, @NonNull Unit<javax.measure.quantity.Mass> massUnit) {
        this.value = normalize(new DecimalNumber(value), massUnit, validator);
    }

    public Mass(@NonNull String quantity) {
        this.value = normalize(QuantityHelper.createQuantity(quantity), validator);
    }

    public static Mass zero() {
        return new Mass("0", KILOGRAM);
    }

    public Quantity<javax.measure.quantity.Mass> getQuantity() {
        return value;
    }

    @Override
    public String toString() {
        return ObjectMapperInstance.write(value);
    }
}
