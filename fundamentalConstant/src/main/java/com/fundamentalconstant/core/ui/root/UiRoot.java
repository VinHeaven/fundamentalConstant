package com.fundamentalconstant.core.ui.root;

import com.fundamentalconstant.core.logic.*;
import com.fundamentalconstant.core.state.*;
import com.fundamentalconstant.core.ui.main.*;
import javafx.application.*;
import javafx.stage.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class UiRoot implements ApplicationListener<UiApplication.StageReadyEvent> {

    @Autowired
    private LogicRoot logicRoot;
    @Autowired
    private StateRoot stateRoot;

    private MainView mainView;

    @Override
    public void onApplicationEvent(UiApplication.StageReadyEvent event) {
        log.info("{} startup", this.getClass().getSimpleName());

        Platform.runLater(() -> {
            Stage stage = event.getStage();

            mainView = new MainView(logicRoot, stateRoot);
            mainView.init(stage);
        });
    }

    public void refresh() {
        Platform.runLater(() -> mainView.refresh());
    }
}
