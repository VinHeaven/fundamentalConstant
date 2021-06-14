package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.attr.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import java.io.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalNumber.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Albedo.AlbedoSerializer.class)
public class Albedo extends DecimalValueValidator {

    @NonNull
    private final DecimalNumber value;

    public Albedo(@NonNull DecimalNumber value) {
        this.value = cleanAndValidate(value);
    }

    @Override
    boolean validate(DecimalNumber value) {
        return value.isBetween(ZERO, ONE);
    }

    public static class AlbedoSerializer extends JsonSerializer<Name> {

        @Override
        public void serialize(Name name, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(name.getValue());
        }
    }
}
