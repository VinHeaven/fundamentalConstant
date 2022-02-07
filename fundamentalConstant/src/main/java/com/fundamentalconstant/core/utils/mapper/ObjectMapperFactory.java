package com.fundamentalconstant.core.utils.mapper;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.paramnames.*;
import com.fundamentalconstant.core.utils.mapper.module.*;
import lombok.experimental.*;
import tech.uom.lib.jackson.*;

import static java.util.Objects.*;

@UtilityClass
public class ObjectMapperFactory {

    private static ObjectMapper objectMapper;

    public static ObjectMapper getMapper() {
        if (isNull(objectMapper)) {
            com.fasterxml.jackson.databind.ObjectMapper om = new com.fasterxml.jackson.databind.ObjectMapper();
            om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NON_PRIVATE);
            om.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
            om.registerModule(new UnitJacksonModule());
            om.registerModule(new QuantityModule());
            om.enable(SerializationFeature.INDENT_OUTPUT);

            objectMapper = new ObjectMapper(om);
        }

        return objectMapper;
    }
}

