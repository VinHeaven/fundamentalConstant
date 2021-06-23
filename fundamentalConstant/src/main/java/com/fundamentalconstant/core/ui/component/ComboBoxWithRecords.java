package com.fundamentalconstant.core.ui.component;

import javafx.scene.control.*;
import javafx.util.*;

import java.util.function.*;
import java.util.stream.*;

import static java.util.Objects.*;
import static javafx.collections.FXCollections.*;

public class ComboBoxWithRecords<T extends Record> extends ComboBox<T> {

    public ComboBoxWithRecords(Stream<T> stream, Consumer<T> consumer) {
        super(observableList(stream.collect(Collectors.toList())));
        this.setCellFactory(createCallback());
        this.setOnAction(e -> consumer.accept(this.getSelectionModel().getSelectedItem()));
    }

    private Callback<ListView<T>, ListCell<T>> createCallback() {
        return recordListView ->
                new ListCell<>() {
                    @Override
                    protected void updateItem(T rec, boolean empty) {
                        super.updateItem(rec, empty);
                        if (isNull(rec) || empty) {
                            setGraphic(null);
                        } else {
                            setText(rec.getName());
                        }
                    }
                };
    }
}
