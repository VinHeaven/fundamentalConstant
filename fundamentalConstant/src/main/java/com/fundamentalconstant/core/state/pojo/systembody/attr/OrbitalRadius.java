package com.fundamentalconstant.core.state.pojo.systembody.attr;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.*;
import lombok.*;

import java.io.*;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(using = OrbitalRadius.OrbitalRadiusSerializer.class)
public class OrbitalRadius {

    @NonNull
    private final Distance value;

    public OrbitalRadius(@NonNull Distance value) {
        this.value = value;
    }

    public OrbitalRadius(String value) {
        this.value = new Distance(value);
    }

    public OrbitalRadius(DecimalNumber value) {
        this.value = new Distance(value);
    }

    public OrbitalRadius(AU value) {
        this.value = new Distance(value.getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static class OrbitalRadiusSerializer extends JsonSerializer<OrbitalRadius> {

        @Override
        public void serialize(OrbitalRadius obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(obj.toString());
        }
    }
}
