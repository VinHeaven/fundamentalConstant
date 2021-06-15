package com.fundamentalconstant.core.ui.control;

import com.fundamentalconstant.core.ui.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Controls extends HBox implements Updater {

    public static Controls create() {
        var controls = new Controls();
        controls.setAlignment(Pos.TOP_LEFT);
        controls.init();
        return controls;
    }

    public void init() {

        this.getChildren().addAll(
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo")
        );
    }

    @Override
    public void refresh() {

    }
}
