package com.fundamentalconstant.core.state.pojo.uuid;

import com.fasterxml.uuid.*;

import java.util.*;

public class UUIDUtils {

    private UUIDUtils() {
    }

    public static UUID getUuid() {
        return Generators.timeBasedGenerator().generate();
    }
}
