package com.fundamentalconstant.core.state.pojo.system;

import com.fundamentalconstant.core.state.pojo.system.attr.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.state.pojo.uuid.*;
import lombok.*;
import lombok.experimental.*;
import lombok.extern.jackson.*;

import java.util.*;

@Data
@SuperBuilder
@Jacksonized
public class StarSystem {

    @NonNull
    @Builder.Default
    private final UUID uuid = UUIDUtils.getUuid();
    @NonNull
    private SystemName name;
    @NonNull
    private Star star;
}
