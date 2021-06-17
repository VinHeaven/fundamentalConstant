package com.fundamentalconstant.core.state.pojo.physics;

import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.utils.mapper.module.*;
import lombok.*;

import javax.measure.*;

public class QuantityUtils {

    private QuantityUtils() {
    }

    @SuppressWarnings("all")
    public static Quantity normalize(@NonNull Quantity<?> quantity, @NonNull DecimalValueValidator validator) {
        return normalize(new DecimalNumber(quantity.getValue().toString()), quantity.getUnit(), validator);
    }

    @SuppressWarnings("all")
    public static Quantity normalize(@NonNull DecimalNumber decimalNumber, @NonNull Unit<?> unit, @NonNull DecimalValueValidator validator) {
        return QuantityHelper.createQuantity(validator.cleanAndValidate(decimalNumber).getValue(), unit);
    }
}