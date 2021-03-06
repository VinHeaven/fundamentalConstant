package com.fundamentalconstant.app;

import com.fundamentalconstant.core.logic.*;
import com.fundamentalconstant.core.state.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class ApplicationRoot {

    private final StateRoot stateRoot;
    private final LogicRoot logicRoot;

    public void start() {
        log.info("{} startup", this.getClass().getSimpleName());

        Registry.setStateRoot(stateRoot);
        Registry.setLogicRoot(logicRoot);

        stateRoot.start();
        logicRoot.start();
    }
}
