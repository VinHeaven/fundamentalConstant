package com.fundamentalconstant.core.state.pojo.physics;

import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;
import lombok.experimental.*;

import javax.measure.*;

import static com.fundamentalconstant.core.utils.QuantityHelper.*;

@UtilityClass
public class QuantityUtils {

    @SuppressWarnings("all")
    public static <T extends Quantity<T>> Quantity<T> normalize(@NonNull Quantity<T> quantity, @NonNull DecimalValueValidator validator) {
        return normalize(new DecimalNumber(quantity.getValue().toString()), quantity.getUnit(), validator);
    }

    @SuppressWarnings("all")
    public static <T extends Quantity<T>> Quantity<T> normalize(@NonNull DecimalNumber decimalNumber, @NonNull Unit<T> unit, @NonNull DecimalValueValidator validator) {
        return createQuantity(validator.cleanAndValidate(decimalNumber).getValue(), unit);
    }
}