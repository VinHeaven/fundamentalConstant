package com.fundamentalconstant.core.ui.component;

import javafx.scene.*;
import javafx.scene.layout.*;

public class HBoxFallTrough extends HBox {

    public HBoxFallTrough() {
        super();
        this.setPickOnBounds(false);
    }

    public HBoxFallTrough(double spacing) {
        super(spacing);
        this.setPickOnBounds(false);
    }

    public HBoxFallTrough(Node... children) {
        super(children);
        this.setPickOnBounds(false);
    }

    public HBoxFallTrough(double spacing, Node... children) {
        super(spacing, children);
        this.setPickOnBounds(false);
    }
}
