package com.fundamentalconstant.core.state.pojo.systembody;

import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.systembody.attr.*;
import lombok.*;
import lombok.experimental.*;
import lombok.extern.jackson.*;

import java.util.*;

@Data
@SuperBuilder
@Jacksonized
public class SystemBody {

    @NonNull
    private SystemBodyName name;
    @NonNull
    private Position position;

    //Physikal Properties
    @NonNull
    private Radius radius;
    @NonNull
    private Mass mass;
    @NonNull
    private Albedo albedo;

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
