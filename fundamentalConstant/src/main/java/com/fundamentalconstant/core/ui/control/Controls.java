package com.fundamentalconstant.core.ui.control;

import com.fundamentalconstant.core.ui.component.*;
import com.fundamentalconstant.core.ui.main.*;
import com.fundamentalconstant.core.ui.window.systemViewWindow.*;
import javafx.event.*;
import javafx.scene.control.*;

public class Controls extends HBoxFallTrough implements Updater {

    public static Controls create() {
        var controls = new Controls();
        controls.init();
        return controls;
    }

    public void init() {
        this.getChildren().addAll(
                create("System View", e -> SystemViewWindow.create().show()),
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo"),
                new Button("Foo")
        );
    }

    private Button create(String header, EventHandler<javafx.event.ActionEvent> onAction) {
        Button systemViewButton = new Button(header);
        systemViewButton.setOnAction(onAction);
        return systemViewButton;
    }

    @Override
    public void refresh() {

    }
}
