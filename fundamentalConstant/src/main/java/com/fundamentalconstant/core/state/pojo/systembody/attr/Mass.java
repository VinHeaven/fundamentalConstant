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
@JsonSerialize(using = Mass.MassSerializer.class)
public class Mass {

    private static final DecimalValueValidator validator = POSITIVE_OR_ZERO;

    @NonNull
    private final DecimalNumber value;

    public Mass(@NonNull DecimalNumber value) {
        this.value = validator.cleanAndValidate(value);
    }

    public Mass(String value) {
        this.value = validator.cleanAndValidate(new DecimalNumber(value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class MassSerializer extends JsonSerializer<Mass> {

        @Override
        public void serialize(Mass obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
