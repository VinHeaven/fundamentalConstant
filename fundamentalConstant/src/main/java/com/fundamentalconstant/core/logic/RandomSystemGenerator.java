package com.fundamentalconstant.core.logic;

import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.system.attr.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import lombok.experimental.*;

import java.math.*;
import java.util.*;

import static com.fundamentalconstant.core.state.pojo.physics.units.Units.*;
import static com.fundamentalconstant.core.utils.QuantityHelper.*;
import static si.uom.SI.*;

@UtilityClass
public class RandomSystemGenerator {

    public static StarSystem generate() {

        StarSystem system = StarSystem.builder()
                .name(new SystemName("Sol"))
                .star(Star.builder()
                        .name(new SystemBodyName("Sun"))
                        .radius(new Radius(createQuantity(new BigDecimal(1_392_700 * 1000 / 2), METRE)))
                        .mass(new Mass(createQuantity(BigDecimal.valueOf(1.989 * Math.pow(10, 30) * 1000), KILOGRAM)))
                        .childs(Set.of(
                                SystemBody.builder()
                                        .name(new SystemBodyName("Mercury"))
                                        .velocity(new Velocity("47.9", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("0.39", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Venus"))
                                        .velocity(new Velocity("35", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("0.72", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Earth"))
                                        .velocity(new Velocity("29.8", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius(createQuantity(1, ASTRONOMICAL_UNIT)))
                                        .childs(Set.of(
                                                SystemBody.builder()
                                                        .name(new SystemBodyName("Luna"))
                                                        .velocity(new Velocity("1.022", KILOMETRE_PER_SECOND))
                                                        .orbitalRadius(new OrbitalRadius("384400", KILOMETRE))
                                                        .build()
                                        ))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Mars"))
                                        .velocity(new Velocity("24.1", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("1.52", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Jupiter"))
                                        .velocity(new Velocity("13.1", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("5.2", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Saturn"))
                                        .velocity(new Velocity("9.7", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("9.54", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Uranus"))
                                        .velocity(new Velocity("6.8", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("19.19", ASTRONOMICAL_UNIT))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Neptune"))
                                        .velocity(new Velocity("5.4", KILOMETRE_PER_SECOND))
                                        .orbitalRadius(new OrbitalRadius("30.06", ASTRONOMICAL_UNIT))
                                        .build()
                        ))
                        .build())
                .build();

        system.refreshSystemBodyRelations();

        return system;
    }
}
