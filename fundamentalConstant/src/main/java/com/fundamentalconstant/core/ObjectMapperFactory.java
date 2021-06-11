package com.fundamentalconstant.core;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.paramnames.*;

public class ObjectMapperFactory {

    private ObjectMapperFactory() {
    }

    public static ObjectMapper getMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NON_PRIVATE);
        om.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        return om;
    }
}
