package com.fundamentalconstant.core.state.pojo.universe;

import com.fundamentalconstant.core.state.pojo.system.*;
import lombok.*;
import lombok.experimental.*;
import lombok.extern.jackson.*;

import java.util.*;

@Data
@SuperBuilder
@Jacksonized
public class Universe {

    @NonNull
    @Builder.Default
    private Set<StarSystem> starSystems = new HashSet<>();
}
