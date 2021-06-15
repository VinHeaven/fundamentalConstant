package com.fundamentalconstant.core.logic;

import com.fundamentalconstant.core.state.pojo.physics.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.system.attr.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;

import java.math.*;
import java.util.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalNumber.*;

public class RandomSystemGenerator {

    public static StarSystem generate() {

        return StarSystem.builder()
                .name(new SystemName("Sol"))
                .star(Star.builder()
                        .name(new SystemBodyName("Sun"))
                        .radius(new Radius(new BigDecimal(1_392_700 * 1000 / 2)))
                        .mass(new Mass(BigDecimal.valueOf(1.989 * Math.pow(10, 30) * 1000)))
                        .velocity(new Velocity(ZERO))
                        .orbitalRadius(new OrbitalRadius(ZERO))
                        .childs(Set.of(
                                SystemBody.builder()
                                        .name(new SystemBodyName("Mercury"))
                                        .velocity(new Velocity("47900"))
                                        .orbitalRadius(new OrbitalRadius(new AU(0.39)))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Venus"))
                                        .velocity(new Velocity("35000"))
                                        .orbitalRadius(new OrbitalRadius(new AU(0.72)))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Earth"))
                                        .velocity(new Velocity("29800"))
                                        .orbitalRadius(new OrbitalRadius(new AU(1)))
                                        .childs(Set.of(
                                                SystemBody.builder()
                                                        .name(new SystemBodyName("Luna"))
                                                        .velocity(new Velocity("10000"))
                                                        .orbitalRadius(new OrbitalRadius(new AU(0.3)))
                                                        .childs(Set.of(
                                                                SystemBody.builder()
                                                                        .name(new SystemBodyName("Test"))
                                                                        .velocity(new Velocity("4500"))
                                                                        .orbitalRadius(new OrbitalRadius(new AU(0.2)))
                                                                        .build()
                                                        ))
                                                        .build()
                                        ))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Mars"))
                                        .velocity(new Velocity("24100"))
                                        .orbitalRadius(new OrbitalRadius(new AU(1.52)))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Jupiter"))
                                        .velocity(new Velocity("13100"))
                                        .orbitalRadius(new OrbitalRadius(new AU(5.2)))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Saturn"))
                                        .velocity(new Velocity("9700"))
                                        .orbitalRadius(new OrbitalRadius(new AU(9.54)))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Uranus"))
                                        .velocity(new Velocity("6800"))
                                        .orbitalRadius(new OrbitalRadius(new AU(19.19)))
                                        .build(),
                                SystemBody.builder()
                                        .name(new SystemBodyName("Neptune"))
                                        .velocity(new Velocity("5400"))
                                        .orbitalRadius(new OrbitalRadius(new AU(30.06)))
                                        .build()
                        ))
                        .build())
                .build();
    }
}
