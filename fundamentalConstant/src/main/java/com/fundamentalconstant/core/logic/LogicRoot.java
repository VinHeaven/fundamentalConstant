package com.fundamentalconstant.core.logic;

import ch.obermuhlner.math.big.*;
import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.state.pojo.geometry.*;
import com.fundamentalconstant.core.state.pojo.geometry.attr.*;
import com.fundamentalconstant.core.state.pojo.system.*;
import com.fundamentalconstant.core.state.pojo.systembody.*;
import com.fundamentalconstant.core.ui.root.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.swing.*;
import java.math.*;

import static com.fundamentalconstant.core.state.pojo.geometry.attr.DecimalNumber.*;
import static com.fundamentalconstant.core.state.pojo.physics.units.DistanceUnit.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class LogicRoot {

    private static final MathContext context = new MathContext(20);
    @Autowired
    private UiRoot uiRoot;
    @Autowired
    private StateRoot stateRoot;

    public void start() {
        log.info("{} startup", this.getClass().getSimpleName());

        var timer = new Timer(100, e -> move());
        timer.setRepeats(true);
        timer.start();
    }

    public void refreshUi() {
        uiRoot.refresh();
    }

    public void move() {
        stateRoot.getUniverse().getStarSystems().forEach(this::moveStarSystem);

        refreshUi();
    }

    private void moveStarSystem(StarSystem starSystem) {
        setNewPosition(starSystem.getStar(), new Position(ZERO, ZERO));
    }

    private void setNewPosition(SystemBody systemBody, Position parentPosition) {

        if (systemBody.getVelocity().getValue().notEqualTo(ZERO) && systemBody.getOrbitalRadius().getValue().getDistance(M).notEqualTo(ZERO)) {
            DecimalNumber distanceTraveled = systemBody.getVelocity().getValue().multiply(new DecimalNumber(1 * 24 * 60 * 60));

            DecimalNumber orbitalRadius = systemBody.getOrbitalRadius().getValue().getDistance(M);
            BigDecimal radTravelled = distanceTraveled.divide(orbitalRadius).getValue();

            Position currentRelativePosition = systemBody.getRelativePosition();
            BigDecimal currentRad = BigDecimal.ZERO;
            if (currentRelativePosition.getX().getValue().notEqualTo(ZERO) && currentRelativePosition.getY().getValue().notEqualTo(ZERO)) {
                currentRad = BigDecimalMath.atan2(currentRelativePosition.getY().getValue().getValue(), currentRelativePosition.getX().getValue().getValue(), context);
            }
            BigDecimal newRad = currentRad.add(radTravelled);

            Position newRelativePosition = new Position(
                    orbitalRadius.multiply(new DecimalNumber(String.valueOf(BigDecimalMath.cos(newRad, context)))),
                    orbitalRadius.multiply(new DecimalNumber(String.valueOf(BigDecimalMath.sin(newRad, context)))));

            systemBody.setRelativePosition(newRelativePosition);
            systemBody.setAbsolutePosition(parentPosition.add(newRelativePosition));
        }

        systemBody.getChilds().forEach(c -> setNewPosition(c, systemBody.getAbsolutePosition()));
    }
}


















