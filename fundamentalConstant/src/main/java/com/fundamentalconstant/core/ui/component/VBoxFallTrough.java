package com.fundamentalconstant.core.ui.component;

import javafx.scene.*;
import javafx.scene.layout.*;

public class VBoxFallTrough extends VBox {

    public VBoxFallTrough() {
        super();
        this.setPickOnBounds(false);
    }

    public VBoxFallTrough(double spacing) {
        super(spacing);
        this.setPickOnBounds(false);
    }

    public VBoxFallTrough(Node... children) {
        super(children);
        this.setPickOnBounds(false);
    }

    public VBoxFallTrough(double spacing, Node... children) {
        super(spacing, children);
        this.setPickOnBounds(false);
    }
}
