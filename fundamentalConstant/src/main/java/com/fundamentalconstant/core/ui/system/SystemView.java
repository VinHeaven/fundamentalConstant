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

        draw(starSystem.getStar(), origin);
    }

    private void draw(SystemBody systemBody, Point2D origin) {

        Circle body = new Circle(0, 0, 3, BLACK);
        body.getTransforms().add(new Translate(-body.getRadius(), -body.getRadius()));
        body.relocate(
                scaleToScreen(systemBody.getPosition().getX(), origin.getX()),
                scaleToScreen(systemBody.getPosition().getY(), origin.getY()));
        this.getChildren().add(body);

        systemBody.getChilds().forEach(c -> draw(c, origin));
    }

    private double scaleToScreen(CartesianCoordinate coordinate, double originCoordinate) {
        return originCoordinate + coordinate.getValue().getValue().divide(new BigDecimal(150_000_000 * 3), RoundingMode.HALF_UP).doubleValue();
    }

    public void init() {

        //        scene.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
        //
        //            @Override
        //            public void handle(ScrollEvent event) {
        //
        //                double delta = 1.2;
        //
        //                double scale = root.getScaleX();
        //                if (event.getDeltaY() < 0) {
        //                    scale /= delta;
        //                } else {
        //                    scale *= delta;
        //                }
        //                //
        //                //                Scale newScale = new Scale();
        //                //                newScale.setX(root.getScaleX() + );
        //                //                newScale.setY(root.getScaleY() + event.getDeltaY()*mult);
        //                //                newScale.setPivotX(root.getScaleX());
        //                //                newScale.setPivotY(root.getScaleY());
        //                //                root.getTransforms().add(newScale);
        //
        //                root.setScaleX(scale);
        //                root.setScaleY(scale);
        //
        //                event.consume();
        //            }
        //        });
    }
}
