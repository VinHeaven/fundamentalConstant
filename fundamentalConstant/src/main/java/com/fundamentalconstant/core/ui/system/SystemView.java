package com.fundamentalconstant.core.ui.system;

import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.ui.*;
import javafx.beans.binding.*;
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
    Pane root;
    BigDecimal oldScale = new BigDecimal(1);
    UUID focusedUiid = null;
    Circle focusedObject = null;
    private StateRoot stateRoot;
    private BigDecimal scaleValue = new BigDecimal(1);
    private Point2D pressedInRootSpace = new Point2D(0, 0);

    public SystemView(StateRoot stateRoot) {
        this.stateRoot = stateRoot;
        this.setPickOnBounds(false);

        this.setBackground(new Background(new BackgroundFill(YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        root = new Pane();
        root.setMaxHeight(0);
        root.setMaxWidth(0);
        root.setPickOnBounds(false);
        root.setBackground(new Background(new BackgroundFill(GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Translate translate = new Translate();
        translate.xProperty().bind(
                Bindings.createDoubleBinding(() -> this.getWidth() / 2,
                        root.boundsInLocalProperty()));
        translate.yProperty().bind(
                Bindings.createDoubleBinding(() -> this.getHeight() / 2,
                        root.boundsInLocalProperty()));

        root.getTransforms().clear();
        root.getTransforms().add(translate);

        this.getChildren().add(root);

        this.setOnScroll(event -> {
            BigDecimal delta = new BigDecimal("1.2");

            if (event.getDeltaY() < 0) {
                scaleValue = scaleValue.divide(delta, RoundingMode.HALF_UP);
            } else {
                scaleValue = scaleValue.multiply(delta);
            }

            if (isNull(focusedUiid)) {
                Point2D mousePositionInLocal = root.sceneToLocal(event.getSceneX(), event.getSceneY());
                Bounds rootBounds = root.getBoundsInLocal();

                double deltaX = mousePositionInLocal.getX() - rootBounds.getCenterX();
                deltaX = deltaX - ((deltaX / oldScale.doubleValue()) * scaleValue.doubleValue());
                root.setTranslateX(root.getTranslateX() + deltaX);

                double deltaY = mousePositionInLocal.getY() - rootBounds.getCenterY();
                deltaY = deltaY - ((deltaY / oldScale.doubleValue()) * scaleValue.doubleValue());
                root.setTranslateY(root.getTranslateY() + deltaY);
            }
            oldScale = scaleValue;

            event.consume();

            this.refresh();
        });

        this.setOnMousePressed(event -> {
            pressedInRootSpace = root.sceneToLocal(event.getSceneX(), event.getSceneY());
        });

        this.setOnMouseDragged(event -> {
            Point2D dragInRootSpace = root.sceneToLocal(event.getSceneX(), event.getSceneY());
            Point2D coordinateInRoot = dragInRootSpace.subtract(pressedInRootSpace);

            root.setTranslateX(root.getTranslateX() + coordinateInRoot.getX());
            root.setTranslateY(root.getTranslateY() + coordinateInRoot.getY());

            event.consume();
        });
    }

    public static SystemView create(StateRoot stateRoot) {
        var systemView = new SystemView(stateRoot);
        systemView.init();
        return systemView;
    }

    private void focusPoint(Point2D pointToFocusInLocal) {
        Bounds rootBounds = root.getBoundsInLocal();
        Point2D thisBoundsInLocal = root.sceneToLocal(new Point2D(this.getWidth() / 2, this.getHeight() / 2));

        double deltaX = pointToFocusInLocal.getX() - thisBoundsInLocal.getX();
        //        deltaX = deltaX - ((deltaX / oldScale.doubleValue()) * scaleValue.doubleValue());
        //        root.setTranslateX(root.getTranslateX() + deltaX);
        //
        double deltaY = pointToFocusInLocal.getY() - thisBoundsInLocal.getY();
        //        deltaY = deltaY - ((deltaY / oldScale.doubleValue()) * scaleValue.doubleValue());
        //        root.setTranslateY(root.getTranslateY() + deltaY);

        root.setTranslateX(root.getTranslateX() - deltaX);
        root.setTranslateY(root.getTranslateY() - deltaY);
    }

    @Override
    public void refresh() {
        root.getChildren().clear();
        focusedObject = null;

        Point2D origin = new Point2D(0, 0);

        StarSystem starSystem = new ArrayList<>(stateRoot.getUniverse().getStarSystems()).get(0);

        draw(starSystem.getStar(), origin, null);

        focus(focusedObject);
    }

    private void focus(Circle focusedObject) {
        if (isNull(focusedObject)) {
            focusedUiid = null;
            return;
        }

        Point2D scenePoint = focusedObject.localToScene(focusedObject.getCenterX(), focusedObject.getCenterY());
        Point2D rootPoint = root.sceneToLocal(scenePoint);
        focusPoint(rootPoint);
    }

    private void draw(SystemBody systemBody, Point2D origin, SystemBody parent) {

        var systemBodyDraw = drawSystemBody(systemBody, origin);
        var orbitDraw = drawOrbit(systemBody, origin, parent);

        root.getChildren().add(systemBodyDraw);
        if (nonNull(orbitDraw)) {
            root.getChildren().add(orbitDraw);
        }
        systemBody.getChilds().forEach(c -> draw(c, origin, systemBody));
    }

    private Circle drawSystemBody(SystemBody systemBody, Point2D origin) {
        Circle body = new Circle(0, 0, 10, BLACK);
        body.getTransforms().add(new Translate(-body.getRadius(), -body.getRadius()));

        body.relocate(
                calculateScreenPosition(systemBody.getAbsolutePosition().getX(), origin.getX()),
                calculateScreenPosition(systemBody.getAbsolutePosition().getY(), origin.getY()));

        body.setOnMousePressed(event -> {
            focusedUiid = systemBody.getUuid();
            focusedObject = body;
            System.out.println("body marked");
        });

        if (systemBody.getUuid().equals(focusedUiid)) {
            focusedObject = body;
        }

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
