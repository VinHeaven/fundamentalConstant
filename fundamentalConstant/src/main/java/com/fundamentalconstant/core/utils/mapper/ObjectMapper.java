package com.fundamentalconstant.core.utils.mapper;

public class ObjectMapper {

    private final com.fasterxml.jackson.databind.ObjectMapper om;

    public ObjectMapper(com.fasterxml.jackson.databind.ObjectMapper om) {
        this.om = om;
    }

    public String write(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            throw new ObjectMapperException(e.getMessage(), e);
        }
    }

    public <T> T read(String src, Class<T> valueType) {
        try {
            return om.readValue(src, valueType);
        } catch (Exception e) {
            throw new ObjectMapperException(e.getMessage(), e);
        }
    }

    class ObjectMapperException extends RuntimeException {

        public ObjectMapperException() {
        }

        public ObjectMapperException(String message) {
            super(message);
        }

        public ObjectMapperException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
