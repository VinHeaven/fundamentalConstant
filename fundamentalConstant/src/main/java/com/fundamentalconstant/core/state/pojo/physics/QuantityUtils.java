package com.fundamentalconstant.core.state.pojo.physics;

import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import javax.measure.*;

import static com.fundamentalconstant.core.utils.QuantityHelper.*;

public class QuantityUtils {

    private QuantityUtils() {
    }

    @SuppressWarnings("all")
    public static Quantity normalize(@NonNull Quantity<?> quantity, @NonNull DecimalValueValidator validator) {
        return normalize(new DecimalNumber(quantity.getValue().toString()), quantity.getUnit(), validator);
    }

    @SuppressWarnings("all")
    public static Quantity normalize(@NonNull DecimalNumber decimalNumber, @NonNull Unit<?> unit, @NonNull DecimalValueValidator validator) {
        return createQuantity(validator.cleanAndValidate(decimalNumber).getValue(), unit);
    }
}