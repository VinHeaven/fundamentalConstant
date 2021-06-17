package com.fundamentalconstant.core.ui.system;

import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.ui.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;

import javax.measure.*;
import javax.measure.quantity.*;
import java.math.*;
import java.util.*;

import static java.util.Objects.*;
import static javafx.scene.paint.Color.*;
import static tech.units.indriya.unit.Units.*;

public class SystemView extends Pane implements Updater {

    private static BigDecimal scaleToScreen = new BigDecimal(150_000_000 * 10);

    private StateRoot stateRoot;
    private DoubleProperty scaleValue;

    public SystemView(StateRoot stateRoot, DoubleProperty scaleValue) {
        this.stateRoot = stateRoot;
        this.scaleValue = scaleValue;
    }

    public static SystemView create(StateRoot stateRoot, DoubleProperty scaleValue) {
        var systemView = new SystemView(stateRoot, scaleValue);
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
                calculateScreenPosition(systemBody.getAbsolutePosition().getX(), origin.getX()),
                calculateScreenPosition(systemBody.getAbsolutePosition().getY(), origin.getY()));
        return body;
    }

    private Circle drawOrbit(SystemBody systemBody, Point2D origin, SystemBody parent) {
        if (isNull(parent)) {
            return null;
        }

        Circle orbit = new Circle(0, 0, scaleToScreen(systemBody.getOrbitalRadius()), TRANSPARENT);
        orbit.getTransforms().add(new Translate(-orbit.getRadius(), -orbit.getRadius()));
        orbit.setStrokeType(StrokeType.CENTERED);
        orbit.setStroke(BLACK);

        orbit.relocate(
                calculateScreenPosition(parent.getAbsolutePosition().getX(), origin.getX()),
                calculateScreenPosition(parent.getAbsolutePosition().getY(), origin.getY()));
        return orbit;
    }

    private double calculateScreenPosition(CartesianCoordinate coordinate, double originCoordinate) {
        return originCoordinate + scaleToScreen(coordinate.getQuantity());
    }

    private double scaleToScreen(Distance distance) {
        return scaleToScreen(distance.getQuantity());
    }

    private double scaleToScreen(Quantity<Length> length) {
        return length.to(METRE).divide(scaleToScreen).to(METRE).getValue().doubleValue() * scaleValue.doubleValue();
    }

    public void init() {
    }
}
