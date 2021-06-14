package com.fundamentalconstant.core.state.pojo;

import com.fasterxml.jackson.core.*;
import com.fundamentalconstant.core.*;
import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StarSystemBodyTest {

    @Test
    void test() throws JsonProcessingException {
        var om = ObjectMapperFactory.getMapper();

        var body1 = SystemBody.builder().position(new Position(XCoordinate.of(5), YCoordinate.of(10))).name(new SystemBodyName("TestName")).build();
        var body2 = SystemBody.builder().position(new Position(XCoordinate.of(5), YCoordinate.of(10))).name(new SystemBodyName("TestName")).build();

        assertEquals(body1, body2);

        var json = om.writeValueAsString(body1);
        System.out.println(json);

        var body3 = om.readValue(json, SystemBody.class);

        System.out.println(om.writeValueAsString(body3));

        var body4 = SystemBody.builder().position(new Position(XCoordinate.of(6), YCoordinate.of(10))).name(new SystemBodyName("TestName")).build();
        var body5 = SystemBody.builder().position(new Position(XCoordinate.of(5), YCoordinate.of(10))).name(new SystemBodyName("TestName")).build();
        assertNotEquals(body4, body5);

        new SystemBodyName("sdfgh");

        body5.addChild(
                SystemBody.builder()
                        .position(new Position(XCoordinate.of(6), YCoordinate.of(10)))
                        .name(new SystemBodyName("TestName"))
                        .build());
    }

    @Test
    public void except() {
        try {
            var om = ObjectMapperFactory.getMapper();
            var body4 = om.readValue("{\"position\":{\"z\":5,\"x\":5,\"y\":10}}", SystemBody.class);

            fail();
        } catch (Exception e) {
        }
    }
}