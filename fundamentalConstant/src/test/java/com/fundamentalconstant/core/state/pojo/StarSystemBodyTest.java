package com.fundamentalconstant.core.state.pojo;

import com.fasterxml.jackson.core.*;
import com.fundamentalconstant.core.logic.*;
import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.units.Mass;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import com.fundamentalconstant.core.state.pojo.uuid.*;
import com.fundamentalconstant.core.utils.mapper.module.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.*;
import si.uom.*;

import javax.measure.*;
import javax.measure.quantity.*;
import java.math.*;

import static org.junit.jupiter.api.Assertions.*;
import static si.uom.SI.*;
import static tech.units.indriya.AbstractUnit.*;

class StarSystemBodyTest {

    @Test
    void test() throws JsonProcessingException {
        var body1 = SystemBody.builder().relativePosition(new Position(new XCoordinate("5", METRE), new YCoordinate("5", METRE))).name(new SystemBodyName("TestName")).build();
        var body2 = SystemBody.builder().relativePosition(new Position(new XCoordinate("5", METRE), new YCoordinate("5", METRE))).name(new SystemBodyName("TestName")).build();

        assertEquals(body1, body2);

        var json = ObjectMapperInstance.write(body1);
        System.out.println(json);

        var body3 = ObjectMapperInstance.read(json, SystemBody.class);

        System.out.println(ObjectMapperInstance.write(body3));

        var body4 = SystemBody.builder().relativePosition(new Position(new XCoordinate("5", METRE), new YCoordinate("5", METRE))).name(new SystemBodyName("TestName")).build();
        var body5 = SystemBody.builder().relativePosition(new Position(new XCoordinate("5", METRE), new YCoordinate("5", METRE))).name(new SystemBodyName("TestName")).build();
        assertNotEquals(body4, body5);

        new SystemBodyName("sdfgh");

        body5.addChild(
                SystemBody.builder()
                        .relativePosition(new Position(new XCoordinate("5", METRE), new YCoordinate("5", METRE)))
                        .name(new SystemBodyName("TestName"))
                        .build());
    }

    @SneakyThrows
    @Test
    void systemBodyTest() {
        var body = SystemBody.builder()
                .name(new SystemBodyName("BodyName"))
                .albedo(new Albedo("0.5"))
                .relativePosition(new Position(new XCoordinate("5", METRE), new YCoordinate("5", METRE)))
                .mass(new Mass("500000.4", KILOGRAM))
                .orbitalRadius(new OrbitalRadius("150000000000", METRE))
                .radius(new Radius("6000000", METRE))
                .velocity(new Velocity("100000.8", METRE_PER_SECOND))
                .build();

        String json = ObjectMapperInstance.write(body);
        System.out.println(json);

        var bodyDeser = ObjectMapperInstance.read(json, SystemBody.class);
        System.out.println(bodyDeser);
    }

    @Test
    void except() {
        try {
            var body4 = ObjectMapperInstance.read("{\"position\":{\"z\":5,\"x\":5,\"y\":10}}", SystemBody.class);

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
        var dist = new Distance(new DecimalNumber(50), METRE);
        System.out.println(dist.getQuantity());
    }

    @Test
    void gak2() {
        var rad = new Radius(new DecimalNumber(50), METRE);
        System.out.println(rad);
    }

    @Test
    void bazgak() {
        System.out.println(UUIDUtils.getUuid());
    }

    @SneakyThrows
    @Test
    void asdff() {
        var system = RandomSystemGenerator.generate();

        var json = ObjectMapperInstance.write(system);
        System.out.println(json);

        var systemDes = ObjectMapperInstance.read(json, StarSystem.class);
        System.out.println(ObjectMapperInstance.write(systemDes));

        var jupiter = systemDes.getStar().getChilds().stream().filter(c -> c.getName().getValue().equals("Jupiter")).findFirst().get();
        System.out.println(new BigDecimal(jupiter.getOrbitalRadius().getQuantity().to(NonSI.ASTRONOMICAL_UNIT).getValue().toString()));

        var radius = new OrbitalRadius("5.2", ASTRONOMICAL_UNIT);
        System.out.println(radius);
        System.out.println(ObjectMapperInstance.write(radius));
        System.out.println(ObjectMapperInstance.read(ObjectMapperInstance.write(radius), OrbitalRadius.class));
    }

    @SneakyThrows
    @Test
    void asdffffff() {
        var dist = new Distance("100", METRE);

        var json = ObjectMapperInstance.write(dist);
        System.out.println(json);

        var distDes = ObjectMapperInstance.read(json, Distance.class);
        System.out.println(distDes);
    }

    @SneakyThrows
    @Test
    void asdffcdffff() {
        var dist = QuantityHelper.createQuantity(100, ASTRONOMICAL_UNIT);
        System.out.println(ObjectMapperInstance.write(dist));

        System.out.println(ObjectMapperInstance.write(dist.toSystemUnit()));
    }

    @SneakyThrows
    @Test
    void asdfffddfff() {
        var albedo = new Albedo(QuantityHelper.createQuantity(0.5, ONE));
        System.out.println(StringUtils.isBlank(albedo.getQuantity().getUnit().getSymbol()));

        var json = ObjectMapperInstance.write(albedo);
        System.out.println(json);

        var albedo1 = ObjectMapperInstance.read(json, Albedo.class);
        System.out.println(albedo1);
    }

    @Test
    void zuuij() {
        Quantity<Length> total = QuantityHelper.createQuantity(2, METRE)
                .add(QuantityHelper.createQuantity(3, METRE));
        assertEquals(5, total.getValue().intValue());
    }

    @SneakyThrows
    @Test
    public void givenUnit_whenProduct_ThenGetProductUnit() {
        Unit<javax.measure.quantity.Area> squareMetre = METRE.multiply(METRE).asType(javax.measure.quantity.Area.class);

        Quantity<Length> line = QuantityHelper.createQuantity(new BigDecimal("11111111111111111111"), METRE);
        assertEquals(line.multiply(line).getUnit(), squareMetre);

        var linesquared = line.multiply(line);
        System.out.println(linesquared);
        System.out.println("-----------------------------------");

        System.out.println(ObjectMapperInstance.write(linesquared));
    }

    @Test
    public void foooghh() {
        var vel = new Velocity("0", METRE_PER_SECOND);
        assertTrue(vel.isZero());
    }
}

























