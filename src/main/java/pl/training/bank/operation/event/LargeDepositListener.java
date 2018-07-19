package pl.training.bank.operation.event;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;

@Log
public class LargeDepositListener implements ApplicationListener<LargeDepositEvent> {

    @Override
    public void onApplicationEvent(LargeDepositEvent largeDepositEvent) {
        log.info("### Large deposit detected: " + largeDepositEvent.getDisposition());
    }

}
