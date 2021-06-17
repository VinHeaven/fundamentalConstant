package com.fundamentalconstant.core.logic;

import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.system.attr.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import com.fundamentalconstant.core.utils.mapper.module.*;

import java.math.*;
import java.util.*;

import static si.uom.SI.*;

public class RandomSystemGenerator {

    public static StarSystem generate() {

        return StarSystem.builder()
                .name(new SystemName("Sol"))
                .star(Star.builder()
                        .name(new SystemBodyName("Sun"))
                        .radius(new Radius(QuantityHelper.createQuantity(new BigDecimal(1_392_700 * 1000 / 2), METRE)))
                        .mass(new Mass(QuantityHelper.createQuantity(BigDecimal.valueOf(1.989 * Math.pow(10, 30) * 1000), KILOGRAM)))
                        .childs(Set.of(
                                SystemBody.builder()
                                        .name(new SystemBodyName("Mercury"))
                                        .velocity(new Velocity("47900", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("0.39", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Venus"))
                                        .velocity(new Velocity("35000", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("0.72", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Earth"))
                                        .velocity(new Velocity("29800", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius(QuantityHelper.createQuantity(1, ASTRONOMICAL_UNIT)))
                                        .childs(Set.of(
                                                SystemBody.builder()
                                                        .name(new SystemBodyName("Luna"))
                                                        .velocity(new Velocity("10000", METRE_PER_SECOND))
                                                        .orbitalRadius(new OrbitalRadius("0.3", ASTRONOMICAL_UNIT))
                                                        .childs(Set.of(
                                                                SystemBody.builder()
                                                                        .name(new SystemBodyName("Test"))
                                                                        .velocity(new Velocity("4500", METRE_PER_SECOND))
                                                                        .orbitalRadius(new OrbitalRadius("0.2", ASTRONOMICAL_UNIT))
                                                                        .build()
                                                        ))
                                                        .build()
                                        ))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Mars"))
                                        .velocity(new Velocity("24100", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("1.52", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Jupiter"))
                                        .velocity(new Velocity("13100", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("5.2", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Saturn"))
                                        .velocity(new Velocity("9700", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("9.54", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Uranus"))
                                        .velocity(new Velocity("6800", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("19.19", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Neptune"))
                                        .velocity(new Velocity("5400", METRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("30.06", ASTRONOMICAL_UNIT))
                                        .build()
                        ))
                        .build())
                .build();
    }
}
