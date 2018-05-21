package pl.training.bank.operation;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import pl.training.bank.disposition.Disposition;

@Aspect
public class LargeDepositLogger implements ApplicationEventPublisherAware {

    private static final long LARGE_DEPOSIT = 9_000;

    private ApplicationEventPublisher publisher;

    @AfterReturning("execution(void pl.training.bank.disposition.DispositionService.process(..)) && args(disposition)")
    public void onLargeDeposit(Disposition disposition) {
        if (disposition.getFunds() >= LARGE_DEPOSIT) {
            LargeDepositEvent largeDepositEvent = new LargeDepositEvent(this);
            largeDepositEvent.setDisposition(disposition);
            publisher.publishEvent(largeDepositEvent);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }

}
