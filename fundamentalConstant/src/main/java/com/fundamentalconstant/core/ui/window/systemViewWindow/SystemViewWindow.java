package com.fundamentalconstant.core.ui.window.systemViewWindow;

import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.ui.component.Record;
import com.fundamentalconstant.core.ui.component.*;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;

import java.util.*;
import java.util.stream.*;

import static com.fundamentalconstant.app.Registry.*;

public class SystemViewWindow extends Stage {

    private SystemViewWindow() {

        ConstrainedGridPane root = new ConstrainedGridPane();
        root.fixColumnSizes(0, 100);
        root.fixRowSizes(5, 25, 50, 25);
        root.setPadding(new Insets(5));

        Pane pane2 = new Pane();
        pane2.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Pane pane3 = new Pane();
        pane3.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        root.add(createSystemSelectionWithSystemInfo(), 0, 0);
        root.add(pane2, 0, 1);
        root.add(pane3, 0, 2);

        Scene secondScene = new Scene(root, 1500, 800);
        this.setTitle("System View");
        this.setScene(secondScene);

        this.setMaximized(true);
    }

    public static SystemViewWindow create() {
        return new SystemViewWindow();
    }

    private Node createSystemSelectionWithSystemInfo() {
        HBox root = new HBox();
        root.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setSpacing(10);

        HBox bar = new HBox();
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setSpacing(10);

        ComboBoxWithRecords<SystemRecord> systemSelection = new ComboBoxWithRecords<>(
                SystemRecord.map().sorted(Comparator.comparing((Record::getName))),
                this::refresh);
        bar.getChildren().add(systemSelection);

        bar.getChildren().add(new DisplayField("Name", "Test"));

        root.getChildren().add(bar);
        return root;
    }

    private void refresh(SystemRecord systemRecord) {

    }

    static class SystemRecord extends Record {
        public SystemRecord(StarSystem starSystem) {
            super(starSystem.getUuid(), starSystem.getName().getValue());
        }

        public static Stream<SystemRecord> map() {
            return getStateRoot().getUniverse().getStarSystems().stream().map(SystemRecord::new);
        }
    }
}
