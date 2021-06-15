package com.fundamentalconstant.core.ui.system;

import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.ui.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;

import java.math.*;
import java.util.*;

import static java.util.Objects.*;
import static javafx.scene.paint.Color.*;

public class SystemView extends Pane implements Updater {

    private StateRoot stateRoot;

    public SystemView(StateRoot stateRoot) {
        this.stateRoot = stateRoot;
    }

    public static SystemView create(StateRoot stateRoot) {
        var systemView = new SystemView(stateRoot);
        systemView.init();
        return systemView;
    }

    @Override
    public void refresh() {
        this.getChildren().clear();

        Point2D origin = new Point2D(this.getBoundsInParent().getCenterX(), this.getBoundsInParent().getCenterY());

        StarSystem starSystem = new ArrayList<>(stateRoot.getUniverse().getStarSystems()).get(0);

        draw(starSystem.getStar(), origin, null);
    }

    private void draw(SystemBody systemBody, Point2D origin, SystemBody parent) {

        var systemBodyDraw = drawSystemBody(systemBody, origin);
        var orbitDraw = drawOrbit(systemBody, origin, parent);

        this.getChildren().add(systemBodyDraw);
        if (nonNull(orbitDraw)) {
            this.getChildren().add(orbitDraw);
        }
        systemBody.getChilds().forEach(c -> draw(c, origin, systemBody));
    }

    private Circle drawSystemBody(SystemBody systemBody, Point2D origin) {
        Circle body = new Circle(0, 0, 3, BLACK);
        body.getTransforms().add(new Translate(-body.getRadius(), -body.getRadius()));

        body.relocate(
                scaleToScreen(systemBody.getAbsolutePosition().getX(), origin.getX()),
                scaleToScreen(systemBody.getAbsolutePosition().getY(), origin.getY()));
        return body;
    }

    private Circle drawOrbit(SystemBody systemBody, Point2D origin, SystemBody parent) {
        if (isNull(parent)) {
            return null;
        }

        Circle orbit = new Circle(0, 0, scaleToScreen(systemBody.getOrbitalRadius().getValue().getValue()), TRANSPARENT);
        orbit.getTransforms().add(new Translate(-orbit.getRadius(), -orbit.getRadius()));
        orbit.setStrokeType(StrokeType.CENTERED);
        orbit.setStroke(BLACK);
        orbit.relocate(
                scaleToScreen(parent.getAbsolutePosition().getX(), origin.getX()),
                scaleToScreen(parent.getAbsolutePosition().getY(), origin.getY()));
        return orbit;
    }

    private double scaleToScreen(CartesianCoordinate coordinate, double originCoordinate) {
        return originCoordinate + scaleToScreen(coordinate.getValue());
    }

    private double scaleToScreen(DecimalNumber coordinate) {
        return coordinate.getValue().divide(new BigDecimal(150_000_000 * 3), RoundingMode.HALF_UP).doubleValue();
    }

    public void init() {
    }
}
