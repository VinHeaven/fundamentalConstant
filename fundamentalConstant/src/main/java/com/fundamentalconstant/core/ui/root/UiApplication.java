package com.fundamentalconstant.core.ui.root;

import com.fundamentalconstant.*;
import javafx.application.*;
import javafx.stage.*;
import org.springframework.boot.builder.*;
import org.springframework.context.*;

public class UiApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(FundamentalConstantApplication.class).run();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void start(Stage stage) {
        this.applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
