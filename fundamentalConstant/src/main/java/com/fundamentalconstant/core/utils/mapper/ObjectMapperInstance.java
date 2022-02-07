package com.fundamentalconstant.core.utils.mapper;

import lombok.experimental.*;

import static java.util.Objects.*;

@UtilityClass
public class ObjectMapperInstance {

    private static ObjectMapper objectMapper;

    public static String write(Object obj) {
        if (isNull(objectMapper)) {
            objectMapper = ObjectMapperFactory.getMapper();
        }
        return objectMapper.write(obj);
    }

    public static <T> T read(String src, Class<T> valueType) {
        if (isNull(objectMapper)) {
            objectMapper = ObjectMapperFactory.getMapper();
        }
        return objectMapper.read(src, valueType);
    }
}