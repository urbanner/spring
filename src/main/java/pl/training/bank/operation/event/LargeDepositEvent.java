package pl.training.bank.operation.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import pl.training.bank.disposition.entity.Disposition;

@Getter
@Setter
public class LargeDepositEvent extends ApplicationEvent {

    private Disposition disposition;

    public LargeDepositEvent(Object source) {
        super(source);
    }

}
