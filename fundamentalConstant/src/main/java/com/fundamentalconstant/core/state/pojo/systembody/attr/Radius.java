package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.physics.*;
import lombok.*;

import java.io.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = Radius.RadiusSerializer.class)
public class Radius {

    @NonNull
    private final Distance value;

    public Radius(@NonNull Distance value) {
        this.value = value;
    }

    public Radius(String value) {
        this.value = new Distance(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class RadiusSerializer extends JsonSerializer<Radius> {

        @Override
        public void serialize(Radius obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
