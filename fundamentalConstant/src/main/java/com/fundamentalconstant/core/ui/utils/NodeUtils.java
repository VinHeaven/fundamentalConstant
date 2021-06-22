package com.fundamentalconstant.core.ui.utils;

import javafx.geometry.*;
import javafx.scene.*;

public class NodeUtils {

    private NodeUtils() {
    }

    public static Point2D getLocalCenter(Node node) {
        Bounds bounds = node.getBoundsInLocal();
        return new Point2D(bounds.getCenterX(), bounds.getCenterY());
    }
}
