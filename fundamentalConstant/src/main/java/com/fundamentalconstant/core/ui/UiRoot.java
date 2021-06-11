package com.fundamentalconstant.core.ui;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class UiRoot {

    public void start() {
        log.info("{} startup", this.getClass().getSimpleName());
    }
}
