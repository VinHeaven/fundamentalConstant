package com.fundamentalconstant.core.utils.mapper.module;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.*;
import com.fundamentalconstant.core.utils.*;

import javax.measure.*;
import java.io.*;

public class QuantityModule extends SimpleModule {

    private static String DIMENSIONLESS_UNIT = "one";

    public QuantityModule() {
        addSerializer(Quantity.class, new QuantitySerializer());
        addDeserializer(Quantity.class, new QuantityDeserializer());
    }

    public static class QuantitySerializer extends JsonSerializer<Quantity> {

        @Override
        public void serialize(Quantity obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            Quantity quantity = obj.toSystemUnit();
            if (quantity.getUnit().toString().equals(DIMENSIONLESS_UNIT)) {
                jgen.writeString(quantity.getValue().toString().replaceAll("0+$", ""));
            } else if (quantity.getValue().toString().contains(".")) {
                jgen.writeString(quantity.getValue().toString().replaceAll("0+$", "") + " " + quantity.getUnit());
            } else {
                jgen.writeString(obj.toSystemUnit().toString());
            }
        }
    }

    public static class QuantityDeserializer extends JsonDeserializer<Quantity> {

        @Override
        public Quantity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            return QuantityHelper.createQuantity(node.asText());
        }
    }
}