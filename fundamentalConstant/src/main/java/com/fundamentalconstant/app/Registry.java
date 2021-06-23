package com.fundamentalconstant.app;

import com.fundamentalconstant.core.logic.*;
import com.fundamentalconstant.core.state.*;

public class Registry {

    private static StateRoot stateRoot = null;
    private static LogicRoot logicRoot = null;

    private Registry() {
    }

    public static StateRoot getStateRoot() {
        return stateRoot;
    }

    public static void setStateRoot(StateRoot stateRoot) {
        Registry.stateRoot = stateRoot;
    }

    public static LogicRoot getLogicRoot() {
        return logicRoot;
    }

    public static void setLogicRoot(LogicRoot logicRoot) {
        Registry.logicRoot = logicRoot;
    }
}
