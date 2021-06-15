package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.exception.*;
import lombok.*;

import java.io.*;
import java.math.*;

@Data
@JsonSerialize(using = DecimalNumber.DecimalNumberSerializer.class)
public class DecimalNumber implements Comparable<DecimalNumber> {

    private static final int DEFAULT_SCALE = 20;
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    private static final int MAX_DOUBLE_PRECISION = 14;

    @NonNull
    private final BigDecimal value;

    public DecimalNumber(String value) {
        this.value = normalize(new BigDecimal(value));
    }

    public DecimalNumber(int value) {
        this.value = normalize(new BigDecimal(value));
    }

    public DecimalNumber(double value) {
        BigDecimal decimal = new BigDecimal(Double.toString(value));
        if (decimal.precision() > MAX_DOUBLE_PRECISION) {
            throw new ProjectException("Possible loss of precision. Use the String constructor instead!");
        }
        this.value = normalize(decimal);
    }

    DecimalNumber(BigDecimal value) {
        this.value = normalize(value);
    }

    private BigDecimal normalize(BigDecimal bigDecimal) {
        return bigDecimal.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public boolean equalTo(DecimalNumber decimalNumber) {
        return this.compareTo(decimalNumber) > 0;
    }

    public boolean greaterThan(DecimalNumber decimalNumber) {
        return this.compareTo(decimalNumber) == 0;
    }

    public boolean lessThan(DecimalNumber decimalNumber) {
        return this.compareTo(decimalNumber) < 0;
    }

    public boolean greaterOrEqualTo(DecimalNumber decimalNumber) {
        return this.compareTo(decimalNumber) >= 0;
    }

    public boolean lessOrEqualTo(DecimalNumber decimalNumber) {
        return this.compareTo(decimalNumber) <= 0;
    }

    public boolean isBetweenOrOn(DecimalNumber decimalNumber1, DecimalNumber decimalNumber2) {
        if (decimalNumber1.lessOrEqualTo(decimalNumber2)) {
            return this.greaterOrEqualTo(decimalNumber1) && this.lessOrEqualTo(decimalNumber2);
        } else {
            return this.greaterOrEqualTo(decimalNumber2) && this.lessOrEqualTo(decimalNumber1);
        }
    }

    public DecimalNumber add(DecimalNumber decimalNumber) {
        return new DecimalNumber(this.value.add(decimalNumber.getValue()));
    }

    public DecimalNumber subtract(DecimalNumber decimalNumber) {
        return new DecimalNumber(this.value.subtract(decimalNumber.getValue()));
    }

    public DecimalNumber multiply(DecimalNumber decimalNumber) {
        return new DecimalNumber(this.value.multiply(decimalNumber.getValue()));
    }

    public DecimalNumber divide(DecimalNumber decimalNumber) {
        return new DecimalNumber(this.value.divide(decimalNumber.getValue(), DEFAULT_ROUNDING_MODE));
    }

    @Override
    public int compareTo(DecimalNumber o) {
        return this.getValue().compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return value.stripTrailingZeros().toPlainString();
    }

    public static class DecimalNumberSerializer extends JsonSerializer<DecimalNumber> {

        @Override
        public void serialize(DecimalNumber obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
