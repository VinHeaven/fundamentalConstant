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
@JsonSerialize(using = Mass.MassSerializer.class)
public class Mass extends DecimalValueValidator {

    @NonNull
    private final DecimalNumber value; --

    public Mass(@NonNull DecimalNumber value) {
        this.value = cleanAndValidate(value);
    }

    @Override
    boolean validate(DecimalNumber value) {
        return value.isBiggerOrEqualThan(ZERO);
    }

    public static class MassSerializer extends JsonSerializer<Mass> {

        @Override
        public void serialize(Mass mass, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(mass.getValue().getValue());
        }
    }
}
