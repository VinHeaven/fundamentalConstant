package com.fundamentalconstant.core.state.pojo.systembody;

import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import com.fundamentalconstant.core.state.pojo.uuid.*;
import lombok.*;
import lombok.experimental.*;
import lombok.extern.jackson.*;

import java.util.*;

import static com.fundamentalconstant.core.state.pojo.physics.units.Distance.*;

@Data
@SuperBuilder
@Jacksonized
public class SystemBody {

    @NonNull
    @Builder.Default
    private final UUID uuid = UUIDUtils.getUuid();
    @NonNull
    private SystemBodyName name;
    @NonNull
    @Builder.Default
    private Position relativePosition = new Position(zero().getQuantity(), zero().getQuantity());
    @NonNull
    @Builder.Default
    private Position absolutePosition = new Position(zero().getQuantity(), zero().getQuantity());


    //Physical Properties
    @NonNull
    @Builder.Default
    private Radius radius = Radius.zero();
    @NonNull
    @Builder.Default
    private Mass mass = Mass.zero();
    @NonNull
    @Builder.Default
    private Albedo albedo = Albedo.zero();

    //Orbital Properties
    @NonNull
    @Builder.Default
    private Velocity velocity = Velocity.zero();
    @NonNull
    @Builder.Default
    private OrbitalRadius orbitalRadius = OrbitalRadius.zero();
    @NonNull
    @Builder.Default
    private Radian currentRadian = Radian.zero();

    @NonNull
    @Builder.Default
    private Set<SystemBody> childs = new HashSet<>();

    public void addChild(SystemBody systemBody) {
        childs.add(systemBody);
    }
}
