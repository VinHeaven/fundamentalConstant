package com.fundamentalconstant.core.state;

import com.fundamentalconstant.core.logic.*;
import com.fundamentalconstant.core.state.pojo.universe.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Getter
@Slf4j
@Component
public class StateRoot {

    private Universe universe;

    public void start() {
        log.info("{} startup", this.getClass().getSimpleName());

        this.universe = RandomUniverseGenerator.generate();
    }
}
