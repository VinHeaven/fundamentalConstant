package com.fundamentalconstant.core.state.pojo.geometry;

import com.fasterxml.jackson.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import java.math.*;

@Data
@AllArgsConstructor
@Builder
public class Position {

    @NonNull
    private final XCoordinate x;

    @NonNull
    private final YCoordinate y;

    private Position(@NonNull BigDecimal x, @NonNull BigDecimal y) {
        this.x = new XCoordinate(x);
        this.y = new YCoordinate(y);
    }

    @JsonCreator
    public Position(String x, String y) {
        this.x = new XCoordinate(x);
        this.y = new YCoordinate(y);
    }

    public Position(DecimalNumber x, DecimalNumber y) {
        this.x = new XCoordinate(x);
        this.y = new YCoordinate(y);
    }

    public Position add(Position position) {
        return new Position(x.getValue().add(position.getX().getValue()), y.getValue().add(position.getY().getValue()));
    }
}
