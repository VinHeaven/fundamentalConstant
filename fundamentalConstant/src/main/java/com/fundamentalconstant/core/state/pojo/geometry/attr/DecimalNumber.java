package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import lombok.*;

import java.io.*;
import java.math.*;

@Data
@JsonSerialize(using = DecimalNumber.DecimalNumberSerializer.class)
public class DecimalNumber implements Comparable<DecimalNumber> {

    public static final DecimalNumber ZERO = new DecimalNumber(BigDecimal.ZERO);
    public static final DecimalNumber ONE = new DecimalNumber(BigDecimal.ZERO);

    private static final int DEFAULT_SCALE = 20;

    @NonNull
    private final BigDecimal value;

    public DecimalNumber(BigDecimal value) {
        this.value = value.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public boolean isEqualTo(DecimalNumber number) {
        return this.compareTo(number) > 0;
    }

    public boolean isBiggerThan(DecimalNumber number) {
        return this.compareTo(number) == 0;
    }

    public boolean isSmallerThan(DecimalNumber number) {
        return this.compareTo(number) < 0;
    }

    public boolean isBiggerOrEqualThan(DecimalNumber number) {
        return this.compareTo(number) >= 0;
    }

    public boolean isSmallerOrEqualThan(DecimalNumber number) {
        return this.compareTo(number) <= 0;
    }

    public boolean isBetween(DecimalNumber number1, DecimalNumber number2) {
        if (number1.isSmallerOrEqualThan(number2)) {
            return this.isBiggerOrEqualThan(number1) && this.isSmallerOrEqualThan(number2);
        } else {
            return this.isBiggerOrEqualThan(number2) && this.isSmallerOrEqualThan(number1);
        }
    }

    @Override
    public int compareTo(DecimalNumber o) {
        return this.getValue().compareTo(o.getValue());
    }

    public static class DecimalNumberSerializer extends JsonSerializer<DecimalNumber> {

        @Override
        public void serialize(DecimalNumber value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(value.getValue());
        }
    }
}
