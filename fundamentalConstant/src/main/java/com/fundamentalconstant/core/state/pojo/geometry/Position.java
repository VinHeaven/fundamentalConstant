package com.fundamentalconstant.core.state.pojo.geometry;

import com.fasterxml.jackson.annotation.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import lombok.*;

import javax.measure.*;
import javax.measure.quantity.*;

import static tech.units.indriya.unit.Units.*;

@Data
@Builder
public class Position {

    @NonNull
    private final XCoordinate x;

    @NonNull
    private final YCoordinate y;

    public Position(@NonNull XCoordinate x, @NonNull YCoordinate y) {
        this.x = x;
        this.y = y;
    }

    public Position(@NonNull Double x, @NonNull Double y) {
        this.x = new XCoordinate(x.toString(), METRE);
        this.y = new YCoordinate(y.toString(), METRE);
    }

    @JsonCreator
    public Position(@NonNull Quantity<Length> x, @NonNull Quantity<Length> y) {
        this.x = new XCoordinate(x);
        this.y = new YCoordinate(y);
    }

    public Position add(Position position) {
        return new Position(
                x.add(position.getX().getQuantity()),
                y.add(position.getY().getQuantity())
        );
    }

    public Position subtract(Position position) {
        return new Position(
                x.subtract(position.getX().getQuantity()),
                y.subtract(position.getY().getQuantity())
        );
    }
}
