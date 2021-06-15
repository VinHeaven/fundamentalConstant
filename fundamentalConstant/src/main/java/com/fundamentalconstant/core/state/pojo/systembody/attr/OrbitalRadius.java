package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import java.io.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalNumber.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = OrbitalRadius.OrbitalRadiusSerializer.class)
public class OrbitalRadius extends DecimalValueValidator {

    @NonNull
    private final DecimalNumber value;

    public OrbitalRadius(@NonNull DecimalNumber value) {
        this.value = cleanAndValidate(value);
    }

    @Override
    boolean validate(DecimalNumber value) {
        return value.isBiggerOrEqualThan(ZERO);
    }

    public static class OrbitalRadiusSerializer extends JsonSerializer<OrbitalRadius> {

        @Override
        public void serialize(OrbitalRadius orbitalRadius, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(orbitalRadius.getValue().getValue());
        }
    }
}
