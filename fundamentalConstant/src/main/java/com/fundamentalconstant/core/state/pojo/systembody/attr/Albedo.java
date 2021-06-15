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
@JsonSerialize(using = Albedo.AlbedoSerializer.class)
public class Albedo {

    private static final DecimalValueValidator validator = ZERO_TO_ONE;

    @NonNull
    private final DecimalNumber value;

    public Albedo(@NonNull DecimalNumber value) {
        this.value = validator.cleanAndValidate(value);
    }

    public Albedo(double value) {
        this.value = validator.cleanAndValidate(new DecimalNumber(value));
    }

    public Albedo(String value) {
        this.value = validator.cleanAndValidate(new DecimalNumber(value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class AlbedoSerializer extends JsonSerializer<Albedo> {

        @Override
        public void serialize(Albedo obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
