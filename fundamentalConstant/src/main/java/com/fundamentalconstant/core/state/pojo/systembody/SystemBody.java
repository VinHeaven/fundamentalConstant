package com.fundamentalconstant.core.state.pojo.systembody;

import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import com.fundamentalconstant.core.state.pojo.uuid.*;
import lombok.*;
import lombok.experimental.*;
import lombok.extern.jackson.*;

import java.util.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalNumber.*;

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
    private Position relativePosition = new Position(ZERO, ZERO);
    @NonNull
    @Builder.Default
    private Position absolutePosition = new Position(ZERO, ZERO);

    //Physical Properties
    @NonNull
    @Builder.Default
    private Radius radius = new Radius(ZERO);
    @NonNull
    @Builder.Default
    private Mass mass = new Mass(ZERO);
    @NonNull
    @Builder.Default
    private Albedo albedo = new Albedo(ZERO);

    //Orbital Properties
    @NonNull
    private Velocity velocity;
    @NonNull
    private OrbitalRadius orbitalRadius;

    @NonNull
    @Builder.Default
    private Set<SystemBody> childs = new HashSet<>();

    public void addChild(SystemBody systemBody) {
        childs.add(systemBody);
    }
}
