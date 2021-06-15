package com.fundamentalconstant.core.state.pojo;

import com.fasterxml.jackson.core.*;
import com.fundamentalconstant.core.*;
import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import lombok.*;
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

    @SneakyThrows
    @Test
    void systemBodyTest() {
        var om = ObjectMapperFactory.getMapper();

        var body = SystemBody.builder()
                .name(new SystemBodyName("BodyName"))
                .albedo(new Albedo("0.5"))
                .position(new Position(new XCoordinate(15), new YCoordinate(20.123)))
                .mass(new Mass("500000.4"))
                .orbitalRadius(new OrbitalRadius("150000000000"))
                .radius(new Radius("6000000"))
                .velocity(new Velocity("100000.8"))
                .build();

        String json = om.writeValueAsString(body);
        System.out.println(json);

        var bodyDeser = om.readValue(json, SystemBody.class);
        System.out.println(bodyDeser);
    }

    @Test
    void except() {
        try {
            var om = ObjectMapperFactory.getMapper();
            var body4 = om.readValue("{\"position\":{\"z\":5,\"x\":5,\"y\":10}}", SystemBody.class);

            fail();
        } catch (Exception e) {
        }
    }

    @Test
    void bar() {
        new DecimalNumber(1.01234);
    }

    @Test
    void gak() {
        var dist = new Distance(new DecimalNumber(50));
        System.out.println(dist.getValue());
    }

    @Test
    void gak2() {
        var rad = new Radius(new Distance(new DecimalNumber(50)));
        System.out.println(rad);
    }
}