package com.fundamentalconstant.core.logic;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class LogicRoot {

    public void start() {
        log.info("{} startup", this.getClass().getSimpleName());
    }
}
