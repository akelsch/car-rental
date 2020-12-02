package de.htwsaar.prog3.carrental.application;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        // TODO move UiApplication logic here
        System.err.println("StageReadyEvent");
    }

}
