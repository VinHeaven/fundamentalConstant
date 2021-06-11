package com.fundamentalconstant.core.state.pojo.system;

import com.fundamentalconstant.core.state.pojo.system.attr.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import lombok.*;
import lombok.experimental.*;
import lombok.extern.jackson.*;

@Data
@SuperBuilder
@Jacksonized
public class System {

    private SystemName name;
    private Star star;
}
