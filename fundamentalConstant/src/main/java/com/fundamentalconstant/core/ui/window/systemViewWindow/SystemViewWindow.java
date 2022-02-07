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
import lombok.extern.log4j.*;

import java.util.*;
import java.util.stream.*;

import static com.fundamentalconstant.app.Registry.*;

@Log4j2
public class SystemViewWindow extends Stage {

    private SystemViewWindow() {

        ConstrainedGridPane root = new ConstrainedGridPane();
        root.fixColumnSizes(0, 100);
        root.fixRowSizes(5, 25, 50, 25);
        root.setPadding(new Insets(5));

        Pane pane2 = new Pane();
        pane2.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        Pane pane3 = new Pane();
        pane3.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        root.add(createSystemSelectionWithSystemInfo(), 0, 0);
        root.add(pane2, 0, 1);
        root.add(pane3, 0, 2);

        Scene secondScene = new Scene(root, 1500, 800);
        this.setTitle("System View");
        this.setScene(secondScene);

        this.setMaximized(false);
    }

    public static SystemViewWindow create() {
        return new SystemViewWindow();
    }

    private Node createSystemSelectionWithSystemInfo() {
        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setSpacing(5);
        root.setPadding(new Insets(5));

        extracted(root);
        extracted(root);

        return root;
    }

    private void extracted(VBox root) {
        HBox bar = new HBox();
        //        bar.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        bar.setMaxHeight(0);
        bar.setSpacing(10);

        bar.getChildren().add(
                new ComboBoxWithRecords<>(
                        SystemRecord.map().sorted(Comparator.comparing((Record::getName))),
                        this::refresh));

        bar.getChildren().add(new DisplayField("Name", "Test"));
        bar.getChildren().add(new DisplayField("Name", "Test2"));

        root.getChildren().add(bar);
    }

    private void refresh(SystemRecord systemRecord) {
        log.info(systemRecord.getName() + " selected!");
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
