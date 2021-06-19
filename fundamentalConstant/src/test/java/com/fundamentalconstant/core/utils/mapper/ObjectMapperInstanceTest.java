package com.fundamentalconstant.core.utils.mapper;

import com.fundamentalconstant.core.state.pojo.physics.units.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static si.uom.SI.*;

class ObjectMapperInstanceTest {

    @Test
    void testSerializationAndDeserializationOfDecimalValues() {

        Distance distance = new Distance("5.2", METRE);
        String json = ObjectMapperInstance.write(distance);
        assertEquals("\"5.20000000000000000000 m\"", json);

        Distance distanceDeser = ObjectMapperInstance.read(json, Distance.class);
        assertEquals(distance, distanceDeser);
    }
}