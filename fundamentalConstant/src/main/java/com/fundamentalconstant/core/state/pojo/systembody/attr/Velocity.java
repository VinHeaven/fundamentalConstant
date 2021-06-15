package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import java.io.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Velocity.VelocitySerializer.class)
public class Velocity extends DecimalValueValidator {

    @NonNull
    private final DecimalNumber value;

    public Velocity(@NonNull DecimalNumber value) {
        this.value = cleanAndValidate(value);
    }

    public static class VelocitySerializer extends JsonSerializer<Velocity> {

        @Override
        public void serialize(Velocity velocity, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(velocity.getValue().getValue());
        }
    }
}
