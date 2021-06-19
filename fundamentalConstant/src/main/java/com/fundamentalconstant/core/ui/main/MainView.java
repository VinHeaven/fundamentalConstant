package com.fundamentalconstant.core.ui.main;

import com.fundamentalconstant.core.logic.*;
import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.ui.*;
import com.fundamentalconstant.core.ui.control.*;
import com.fundamentalconstant.core.ui.system.*;
import javafx.geometry.*;
import javafx.scene.*;
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

    public void init(Stage stage) {
        this.stage = stage;

        root = new AnchorPane();

        scene = new Scene(root, 500, 500, true, SceneAntialiasing.BALANCED);

        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        stage.setTitle("Sample Application");
        //        stage.setMaximized(true);
        stage.setScene(scene);

        stack = new StackPane();
        stack.setAlignment(Pos.TOP_LEFT);
        stack.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(stack);
        AnchorPane.setBottomAnchor(stack, 0.0);
        AnchorPane.setTopAnchor(stack, 0.0);
        AnchorPane.setRightAnchor(stack, 0.0);
        AnchorPane.setLeftAnchor(stack, 0.0);

        stack.getChildren().addAll(
                SystemView.create(stateRoot),
                Controls.create()
        );

        stage.show();
    }

    public void refresh() {
        stack.getChildren().forEach(c -> ((Updater) c).refresh());
    }
}
