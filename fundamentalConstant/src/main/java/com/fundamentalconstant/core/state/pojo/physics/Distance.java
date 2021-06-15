package com.fundamentalconstant.core.state.pojo.physics;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.exception.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import lombok.*;

import java.io.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.IntegerNumber.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Distance.DistanceSerializer.class)
public class Distance extends IntegerValueValidator {

    @NonNull
    //Distance in Meter
    private final IntegerNumber value;

    public Distance(@NonNull IntegerNumber value) {
        this.value = cleanAndValidate(value);
    }

    @Override
    boolean validate(IntegerNumber value) {
        return value.isBiggerOrEqualThan(ZERO);
    }

    public IntegerNumber getDistance(DistanceUnit unit) {
        return switch (unit) {
            case M -> value;
            case KM -> value.divide(new IntegerNumber(1000));
            default -> throw new NoCaseDefinedException("Unexpected value: " + unit);
        };
    }

    public static class DistanceSerializer extends JsonSerializer<Distance> {

        @Override
        public void serialize(Distance distance, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(distance.getValue().getValue());
        }
    }
}
