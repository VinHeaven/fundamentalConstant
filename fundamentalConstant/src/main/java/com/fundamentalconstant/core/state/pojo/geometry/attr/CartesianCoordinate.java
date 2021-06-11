package com.fundamentalconstant.core.state.pojo.geometry.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import lombok.*;

import java.io.*;
import java.math.*;

@Data
@JsonSerialize(using = CartesianCoordinate.CartesianCoordinateSerializer.class)
public abstract class CartesianCoordinate {

    @NonNull
    private final BigInteger value;

    public CartesianCoordinate(@NonNull BigInteger value) {
        this.value = value;
    }

    public CartesianCoordinate(int value) {
        this.value = BigInteger.valueOf(value);
    }

    public CartesianCoordinate(long value) {
        this.value = BigInteger.valueOf(value);
    }

    public CartesianCoordinate(double value) {
        this.value = BigInteger.valueOf(Math.round(value));
    }

    public BigInteger getValue() {
        return value;
    }

    public static class CartesianCoordinateSerializer extends JsonSerializer<CartesianCoordinate> {

        @Override
        public void serialize(CartesianCoordinate value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(value.getValue());
        }
    }
}
