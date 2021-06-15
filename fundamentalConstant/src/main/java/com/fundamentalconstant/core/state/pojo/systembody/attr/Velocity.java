package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import java.io.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalValueValidator.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Velocity.VelocitySerializer.class)
public class Velocity {

    private static final DecimalValueValidator validator = NONE;

    @NonNull
    private final DecimalNumber value;

    public Velocity(@NonNull DecimalNumber value) {
        this.value = validator.cleanAndValidate(value);
    }

    public Velocity(String value) {
        this.value = validator.cleanAndValidate(new DecimalNumber(value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class VelocitySerializer extends JsonSerializer<Velocity> {

        @Override
        public void serialize(Velocity obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
