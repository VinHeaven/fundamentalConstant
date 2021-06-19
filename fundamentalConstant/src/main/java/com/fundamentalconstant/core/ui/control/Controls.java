package com.fundamentalconstant.core.ui.control;

import com.fundamentalconstant.core.ui.*;
import com.fundamentalconstant.core.ui.component.*;
import javafx.scene.control.*;

public class Controls extends HBoxFallTrough implements Updater {

    public static Controls create() {
        var controls = new Controls();
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
