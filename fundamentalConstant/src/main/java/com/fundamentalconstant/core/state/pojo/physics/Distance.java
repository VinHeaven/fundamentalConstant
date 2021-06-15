package com.fundamentalconstant.core.state.pojo.physics;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import lombok.*;

import java.io.*;
import java.math.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalValueValidator.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Distance.DistanceSerializer.class)
public class Distance {

    private static final DecimalValueValidator validator = POSITIVE_OR_ZERO;

    private static final DistanceUnit unit = DistanceUnit.M;

    @NonNull
    private final DecimalNumber value;

    public Distance(@NonNull DecimalNumber value) {
        this.value = validator.cleanAndValidate(value);
    }

    public Distance(BigDecimal value) {
        this.value = validator.cleanAndValidate(new DecimalNumber(value));
    }

    public Distance(String value) {
        this.value = validator.cleanAndValidate(new DecimalNumber(value));
    }

    public DecimalNumber getDistance(DistanceUnit targetUnit) {
        if (unit.equals(targetUnit)) {
            return this.value;
        }

        return this.value.multiply(DistanceUnit.getFactor(unit, targetUnit));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class DistanceSerializer extends JsonSerializer<Distance> {

        @Override
        public void serialize(Distance obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
