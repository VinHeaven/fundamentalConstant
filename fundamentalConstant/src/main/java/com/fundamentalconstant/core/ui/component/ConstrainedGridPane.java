package com.fundamentalconstant.core.ui.component;

import javafx.scene.layout.*;

public class ConstrainedGridPane extends GridPane {

    public void fixColumnSizes(int hGap, int... colWidths) {
        this.setHgap(hGap);
        // Setting columns size in percent
        for (int colWidth : colWidths) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(colWidth);
            getColumnConstraints().add(column);
        }
    }

    public void fixRowSizes(int vGap, int... rowHeight) {
        this.setVgap(vGap);
        for (int rowWidth : rowHeight) {
            RowConstraints rowc = new RowConstraints();
            rowc.setPercentHeight(rowWidth);
            getRowConstraints().add(rowc);
        }

        this.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }
}
