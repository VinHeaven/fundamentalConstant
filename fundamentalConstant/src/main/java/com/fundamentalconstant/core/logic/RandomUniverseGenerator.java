package com.fundamentalconstant.core.logic;

import com.fundamentalconstant.core.state.pojo.universe.*;

import java.util.*;

public class RandomUniverseGenerator {

    public static Universe generate() {
        return Universe.builder()
                .starSystems(Set.of(
                        RandomSystemGenerator.generate()
                ))
                .build();
    }
}
