package com.fundamentalconstant.core.ui.system;

import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.ui.*;
import com.fundamentalconstant.core.ui.utils.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.*;

import javax.measure.*;
import javax.measure.quantity.*;
import java.math.*;
import java.util.*;

import static com.fundamentalconstant.core.ui.utils.NodeUtils.*;
import static java.util.Objects.*;
import static javafx.scene.paint.Color.*;
import static tech.units.indriya.unit.Units.*;

public class SystemView extends BorderPane implements Updater {

    private static BigDecimal scaleToScreen = new BigDecimal(150_000_000 * 10);
    double oldScale = 1;
    private Pane root;
    private UUID focusedUiid = null;
    private Circle focusedObject = null;
    private StateRoot stateRoot;
    private double scaleValue = 1;
    private Point2D pressedInRootSpace = new Point2D(0, 0);

    private SystemView(StateRoot stateRoot) {
        this.stateRoot = stateRoot;

        root = new Pane();
        root.setMaxHeight(0);
        root.setMaxWidth(0);

        this.centerProperty().set(root);

        addZoomListener();
        addPanListener();
        addResizeListener();
    }

    public static SystemView create(StateRoot stateRoot) {
        return new SystemView(stateRoot);
    }

    private void addResizeListener() {
        var resizeTimer = new EventTimer();
        this.widthProperty().addListener(e -> {
            if (resizeTimer.hasTimeElapsed()) {
                refresh();
            }
        });
        this.heightProperty().addListener(e -> {
            if (resizeTimer.hasTimeElapsed()) {
                refresh();
            }
        });
    }

    private void addPanListener() {
        this.setOnMousePressed(event -> pressedInRootSpace = root.sceneToLocal(event.getSceneX(), event.getSceneY()));

        var dragTimer = new EventTimer();
        this.setOnMouseDragged(event -> {
            focusedUiid = null;

            if (dragTimer.hasTimeElapsed()) {
                Point2D dragInRootSpace = root.sceneToLocal(event.getSceneX(), event.getSceneY());
                Point2D coordinateInRoot = dragInRootSpace.subtract(pressedInRootSpace);

                root.setTranslateX(root.getTranslateX() + coordinateInRoot.getX());
                root.setTranslateY(root.getTranslateY() + coordinateInRoot.getY());
            }

            event.consume();
        });
    }

    private void addZoomListener() {
        var zoomTimer = new EventTimer();
        this.setOnScroll(event -> {
            if (zoomTimer.hasTimeElapsed()) {
                double delta = 1.2;

                if (event.getDeltaY() < 0) {
                    scaleValue = scaleValue / delta;
                } else {
                    scaleValue = scaleValue * delta;
                }

                if (isNull(focusedUiid)) {
                    Point2D mousePositionInLocal = root.sceneToLocal(event.getSceneX(), event.getSceneY());
                    Point2D rootCenter = getLocalCenter(root);

                    double deltaX = mousePositionInLocal.getX() - rootCenter.getX();
                    deltaX = deltaX - ((deltaX / oldScale) * scaleValue);
                    root.setTranslateX(root.getTranslateX() + deltaX);

                    double deltaY = mousePositionInLocal.getY() - rootCenter.getY();
                    deltaY = deltaY - ((deltaY / oldScale) * scaleValue);
                    root.setTranslateY(root.getTranslateY() + deltaY);
                }
                oldScale = scaleValue;

                this.refresh();
            }

            event.consume();
        });
    }

    @Override
    public void refresh() {
        root.getChildren().clear();
        focusedObject = null;

        StarSystem starSystem = new ArrayList<>(stateRoot.getUniverse().getStarSystems()).get(0);

        Point2D origin = getLocalCenter(root);
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
        Point2D thisBoundsInLocal = root.sceneToLocal(new Point2D(this.getWidth() / 2, this.getHeight() / 2));

        double deltaX = rootPoint.getX() - thisBoundsInLocal.getX();
        double deltaY = rootPoint.getY() - thisBoundsInLocal.getY();

        root.setTranslateX(root.getTranslateX() - deltaX);
        root.setTranslateY(root.getTranslateY() - deltaY);
    }

    private void draw(SystemBody systemBody, Point2D origin, SystemBody parent) {

        var systemBodyDrawn = drawSystemBody(systemBody, origin);
        var orbitDrawn = drawOrbit(systemBody, origin, parent);

        List<Node> objectsToDraw = List.of(
                systemBodyDrawn,
                orbitDrawn);

        hideObjectsIfToCloseToParent(systemBody, objectsToDraw);

        root.getChildren().addAll(objectsToDraw);

        systemBody.getChilds().forEach(c -> draw(c, origin, systemBody));
    }

    private void hideObjectsIfToCloseToParent(SystemBody systemBody, List<Node> objectsToDraw) {
        Distance distanceBetweenChildAndParent = new Distance(systemBody.getOrbitalRadius().getQuantity());
        if (scaleToScreen(distanceBetweenChildAndParent) < 5) {
            objectsToDraw.forEach(n -> n.setVisible(false));
        }
    }

    private Pane drawSystemBody(SystemBody systemBody, Point2D origin) {
        double bodyRadiusInPixel = 7;
        Pane pane = new Pane();

        Circle body = new Circle(0, 0, bodyRadiusInPixel, BLACK);
        body.setOnMousePressed(event -> {
            focusedUiid = systemBody.getUuid();
            focusedObject = body;
        });

        if (systemBody.getUuid().equals(focusedUiid)) {
            focusedObject = body;
        }

        pane.getChildren().add(body);

        Text name = new Text(systemBody.getName().getValue());
        name.getTransforms().add(new Translate(-name.getBoundsInLocal().getWidth() / 2, bodyRadiusInPixel * 2));
        pane.getChildren().add(name);

        pane.relocate(
                calculateScreenPosition(systemBody.getAbsolutePosition().getX(), origin.getX()),
                calculateScreenPosition(systemBody.getAbsolutePosition().getY(), origin.getY()));

        return pane;
    }

    private Circle drawOrbit(SystemBody systemBody, Point2D origin, SystemBody parent) {
        Circle orbit = new Circle(0, 0, scaleToScreen(systemBody.getOrbitalRadius()), TRANSPARENT);
        orbit.getTransforms().add(new Translate(-orbit.getRadius(), -orbit.getRadius()));
        orbit.setStrokeType(StrokeType.CENTERED);
        orbit.setStroke(BLACK);
        orbit.setMouseTransparent(true);

        Position parentAbsolutePosition = nonNull(parent) ? parent.getAbsolutePosition() : new Position(origin.getX(), origin.getY());

        orbit.relocate(
                calculateScreenPosition(parentAbsolutePosition.getX(), origin.getX()),
                calculateScreenPosition(parentAbsolutePosition.getY(), origin.getY()));
        return orbit;
    }

    private double calculateScreenPosition(CartesianCoordinate coordinate, double originCoordinate) {
        return originCoordinate + scaleToScreen(coordinate.getQuantity());
    }

    private double scaleToScreen(Distance distance) {
        return scaleToScreen(distance.getQuantity());
    }

    private double scaleToScreen(Quantity<Length> length) {
        return length.to(METRE).divide(scaleToScreen).to(METRE).getValue().doubleValue() * scaleValue;
    }
}
