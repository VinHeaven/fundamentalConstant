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

    @JsonCreator
    private Position(@NonNull BigDecimal x, @NonNull BigDecimal y) {
        this.x = XCoordinate.of(x);
        this.y = YCoordinate.of(y);
    }
}
