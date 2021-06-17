package com.fundamentalconstant.core.state.pojo.physics.units;

import com.fasterxml.jackson.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.utils.mapper.module.*;
import lombok.*;
import tech.units.indriya.*;

import javax.measure.*;
import javax.measure.quantity.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalValueValidator.*;
import static com.fundamentalconstant.core.state.pojo.physics.QuantityUtils.*;
import static tech.units.indriya.AbstractUnit.*;

@EqualsAndHashCode
public class Albedo {

    private static final DecimalValueValidator validator = ZERO_TO_ONE;

    @NonNull
    @JsonValue
    private final Quantity<Dimensionless> value;

    public Albedo(@NonNull Quantity<Dimensionless> albedo) {
        this.value = normalize(albedo, validator);
    }

    public Albedo(@NonNull DecimalNumber decimalNumber) {
        this.value = normalize(decimalNumber, ONE, validator);
    }

    public Albedo(@NonNull String quantity) {
        this.value = normalize(AbstractQuantity.parse(quantity), validator);
    }

    public static Albedo zero() {
        return new Albedo(QuantityHelper.createQuantity(0, ONE));
    }

    public Quantity<Dimensionless> getQuantity() {
        return value;
    }

    @Override
    public String toString() {
        return ObjectMapperInstance.write(value);
    }
}
