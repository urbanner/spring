package pl.training.bank.disposition;

import org.aspectj.lang.annotation.*;
import pl.training.bank.BankException;

@Aspect
public class SzymonLogger {

    private static final String SEPARATOR = "SUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU###########################################################################";

    @AfterReturning("execution(void pl.training.bank.disposition.DispositionService.process(..)) && args(disposition)")
    public void check(Disposition disposition) {
        if (disposition.getFunds() > 1_000_000) {
            System.out.format("%s\n%s\n", SEPARATOR, disposition);
        }
    }
}
