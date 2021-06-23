package com.fundamentalconstant.core.ui.component;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DisplayField extends HBox {

    public DisplayField(String label, String value) {
        super();

        Label label1 = new Label(label + ":");
        Label label2 = new Label(value);
        this.getChildren().addAll(label1, label2);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(2);
    }
}
