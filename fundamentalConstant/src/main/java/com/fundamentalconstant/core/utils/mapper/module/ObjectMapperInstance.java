package com.fundamentalconstant.core.utils.mapper.module;

import com.fundamentalconstant.core.utils.mapper.*;

import static java.util.Objects.*;

public class ObjectMapperInstance {

    private static ObjectMapper objectMapper;

    private ObjectMapperInstance() {
    }

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