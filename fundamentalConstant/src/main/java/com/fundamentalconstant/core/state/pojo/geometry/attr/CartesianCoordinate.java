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
    private final DecimalNumber value;

    public CartesianCoordinate(@NonNull BigDecimal value) {
        this.value = new DecimalNumber(value);
    }

    public CartesianCoordinate(String value) {
        this.value = new DecimalNumber(value);
    }

    public CartesianCoordinate(int value) {
        this.value = new DecimalNumber(value);
    }

    public CartesianCoordinate(long value) {
        this.value = new DecimalNumber(value);
    }

    public CartesianCoordinate(double value) {
        this.value = new DecimalNumber(value);
    }

    public CartesianCoordinate(DecimalNumber value) {
        this.value = value;
    }

    public DecimalNumber getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class CartesianCoordinateSerializer extends JsonSerializer<CartesianCoordinate> {

        @Override
        public void serialize(CartesianCoordinate obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
