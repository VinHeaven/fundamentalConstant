package com.fundamentalconstant.core.ui.main;

import com.fundamentalconstant.core.logic.*;
import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.ui.*;
import com.fundamentalconstant.core.ui.control.*;
import com.fundamentalconstant.core.ui.system.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import jfxtras.styles.jmetro.*;
import lombok.*;

@RequiredArgsConstructor
public class MainView {

    private final LogicRoot logicRoot;
    private final StateRoot stateRoot;

    private Stage stage;
    private Scene scene;
    private AnchorPane root;
    private StackPane stack;

    private DoubleProperty scaleValue = new SimpleDoubleProperty(1d);

    public void init(Stage stage) {
        this.stage = stage;

        root = new AnchorPane();

        SystemView systemView = SystemView.create(stateRoot, scaleValue);

        scene = new Scene(root, 500, 500, true, SceneAntialiasing.BALANCED);
        scene.addEventFilter(ScrollEvent.ANY, event -> {
            double delta = 1.2;

            if (event.getDeltaY() < 0) {
                scaleValue.set(scaleValue.get() / delta);
            } else {
                scaleValue.set(scaleValue.get() * delta);
            }

            event.consume();
        });

        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        stage.setTitle("Sample Application");
        //        stage.setMaximized(true);
        stage.setScene(scene);

        stack = new StackPane();
        stack.setAlignment(Pos.CENTER);
        stack.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(stack);
        AnchorPane.setBottomAnchor(stack, 0.0);
        AnchorPane.setTopAnchor(stack, 0.0);
        AnchorPane.setRightAnchor(stack, 0.0);
        AnchorPane.setLeftAnchor(stack, 0.0);

        stack.getChildren().addAll(
                systemView,
                Controls.create()
        );

        stage.show();
    }

    public void refresh() {
        stack.getChildren().forEach(c -> ((Updater) c).refresh());
    }
}
