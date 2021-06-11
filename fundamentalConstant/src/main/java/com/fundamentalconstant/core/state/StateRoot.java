package com.fundamentalconstant.core.state;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class StateRoot {

    public void start() {
        log.info("{} startup", this.getClass().getSimpleName());
    }
}
