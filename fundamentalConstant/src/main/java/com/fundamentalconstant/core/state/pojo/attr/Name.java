package com.fundamentalconstant.core.state.pojo.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import lombok.*;

import java.io.*;

import static org.apache.commons.lang3.StringUtils.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Name.NameSerializer.class)
public abstract class Name extends StringValue {

    @NonNull
    private final String value;

    public Name(@NonNull String value) {
        this.value = cleanAndValidate(value);
    }

    @Override
    boolean validate(String value) {
        return isNotBlank(value);
    }

    @Override
    String clean(String value) {
        return strip(value);
    }

    public static class NameSerializer extends JsonSerializer<Name> {

        @Override
        public void serialize(Name name, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(name.getValue());
        }
    }
}
