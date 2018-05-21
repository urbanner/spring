package pl.training.bank.common;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@Log
public class ContextListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        log.info("### Spring context is going down...");
    }

}
