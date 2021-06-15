package com.fundamentalconstant.core.state.pojo.physics.units;

import com.fundamentalconstant.core.state.pojo.geometry.attr.*;

public enum DistanceUnit {
    M,
    KM;

    public static DecimalNumber getFactor(DistanceUnit from, DistanceUnit to) {

        return switch (from) {
            case M -> switch (to) {
                case M -> new DecimalNumber(1);
                case KM -> new DecimalNumber(0.001);
            };
            case KM -> switch (to) {
                case M -> new DecimalNumber(1000);
                case KM -> new DecimalNumber(1);
            };
        };
    }
}