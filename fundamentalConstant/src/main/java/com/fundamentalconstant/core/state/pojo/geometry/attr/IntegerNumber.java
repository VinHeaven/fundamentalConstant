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

    @NonNull
    private final BigInteger value;

    @JsonCreator
    public IntegerNumber(BigInteger value) {
        this.value = value;
    }

    public IntegerNumber(Integer value) {
        this.value = BigInteger.valueOf(value);
    }

    public boolean equalTo(IntegerNumber integerNumber) {
        return this.compareTo(integerNumber) == 0;
    }

    public boolean greaterThan(IntegerNumber integerNumber) {
        return this.compareTo(integerNumber) > 0;
    }

    public boolean lessThan(IntegerNumber integerNumber) {
        return this.compareTo(integerNumber) < 0;
    }

    public boolean greaterOrEqualTo(IntegerNumber integerNumber) {
        return this.compareTo(integerNumber) >= 0;
    }

    public boolean lessOrEqualTo(IntegerNumber integerNumber) {
        return this.compareTo(integerNumber) <= 0;
    }

    public boolean isBetweenOrOn(IntegerNumber integerNumber1, IntegerNumber integerNumber2) {
        if (integerNumber1.lessOrEqualTo(integerNumber2)) {
            return this.greaterOrEqualTo(integerNumber1) && this.lessOrEqualTo(integerNumber2);
        } else {
            return this.greaterOrEqualTo(integerNumber2) && this.lessOrEqualTo(integerNumber1);
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
