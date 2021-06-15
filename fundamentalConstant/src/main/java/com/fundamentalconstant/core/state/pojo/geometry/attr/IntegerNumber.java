package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import lombok.*;

import java.io.*;
import java.math.*;

@Data
@JsonSerialize(using = IntegerNumber.IntegerNumberSerializer.class)
public class IntegerNumber implements Comparable<IntegerNumber> {

    public static final IntegerNumber ZERO = new IntegerNumber(BigInteger.ZERO);
    public static final IntegerNumber ONE = new IntegerNumber(BigInteger.ZERO);

    @NonNull
    private final BigInteger value;

    @JsonCreator
    public IntegerNumber(BigInteger value) {
        this.value = value;
    }

    public IntegerNumber(Integer value) {
        this.value = BigInteger.valueOf(value);
    }

    public boolean isEqualTo(IntegerNumber number) {
        return this.compareTo(number) > 0;
    }

    public boolean isBiggerThan(IntegerNumber number) {
        return this.compareTo(number) == 0;
    }

    public boolean isSmallerThan(IntegerNumber number) {
        return this.compareTo(number) < 0;
    }

    public boolean isBiggerOrEqualThan(IntegerNumber number) {
        return this.compareTo(number) >= 0;
    }

    public boolean isSmallerOrEqualThan(IntegerNumber number) {
        return this.compareTo(number) <= 0;
    }

    public boolean isBetween(IntegerNumber number1, IntegerNumber number2) {
        if (number1.isSmallerOrEqualThan(number2)) {
            return this.isBiggerOrEqualThan(number1) && this.isSmallerOrEqualThan(number2);
        } else {
            return this.isBiggerOrEqualThan(number2) && this.isSmallerOrEqualThan(number1);
        }
    }

    public IntegerNumber divide(IntegerNumber number) {
        BigDecimal decimal = new BigDecimal(this.getValue()).divide(new BigDecimal(number.getValue()), RoundingMode.HALF_UP);
        return new IntegerNumber(decimal.toBigInteger());
    }

    @Override
    public int compareTo(IntegerNumber o) {
        return this.getValue().compareTo(o.getValue());
    }

    public static class IntegerNumberSerializer extends JsonSerializer<IntegerNumber> {

        @Override
        public void serialize(IntegerNumber value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(value.getValue());
        }
    }
}
