package com.fundamentalconstant.core.state.pojo.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import lombok.*;

import java.io.*;

import static com.fundamentalconstant.core.state.pojo.attr.StringValueValidator.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Name.NameSerializer.class)
public abstract class Name {

    private static final StringValueValidator validator = NOT_BLANK;

    @NonNull
    private final String value;

    public Name(@NonNull String value) {
        this.value = validator.cleanAndValidate(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public static class NameSerializer extends JsonSerializer<Name> {

        @Override
        public void serialize(Name obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
