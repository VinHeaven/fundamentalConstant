package com.fundamentalconstant.core.logic;

import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.physics.units.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.ui.root.*;
import com.fundamentalconstant.core.utils.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.measure.*;
import javax.measure.quantity.*;
import javax.swing.*;

import static com.fundamentalconstant.core.state.pojo.physics.units.Distance.*;
import static tech.units.indriya.unit.Units.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class LogicRoot {

    @Autowired
    private UiRoot uiRoot;
    @Autowired
    private StateRoot stateRoot;

    public void start() {
        log.info("{} startup", this.getClass().getSimpleName());

        var timer = new Timer(500, e -> move());
        timer.setRepeats(true);
        timer.start();
    }

    public void refreshUi() {
        uiRoot.refresh();
    }

    @SneakyThrows
    public void move() {
        stateRoot.getUniverse().getStarSystems().forEach(this::moveStarSystem);
        refreshUi();
    }

    private void moveStarSystem(StarSystem starSystem) {
        setNewPosition(starSystem.getStar(), new Position(zero().getQuantity(), zero().getQuantity()));
    }

    private void setNewPosition(SystemBody systemBody, Position parentPosition) {

        if (systemBodyMoves(systemBody)) {

            var distanceTraveled = systemBody.getVelocity().getQuantity().multiply(QuantityHelper.createQuantity(1 * 24 * 60 * 60, SECOND));

            var orbitalRadius = systemBody.getOrbitalRadius().getQuantity();
            var radTravelled = distanceTraveled.divide(orbitalRadius).asType(Angle.class);

            Quantity<Angle> currentRad = systemBody.getCurrentRadian().getQuantity();

            var newRad = currentRad.add(radTravelled);
            systemBody.setCurrentRadian(new Radian(newRad));
            var radNormalized = newRad.to(RADIAN).getValue().doubleValue();

            Position newRelativePosition = new Position(
                    orbitalRadius.multiply(Math.cos(radNormalized)),
                    orbitalRadius.multiply(Math.sin(radNormalized))
            );

            systemBody.setRelativePosition(newRelativePosition);
            systemBody.setAbsolutePosition(parentPosition.add(newRelativePosition));
        }

        systemBody.getChilds().forEach(c -> setNewPosition(c, systemBody.getAbsolutePosition()));
    }

    private boolean systemBodyMoves(SystemBody systemBody) {
        return systemBody.getVelocity().isNotZero();
    }
}


















