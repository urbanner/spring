package pl.training.bank.operation;

import org.aspectj.lang.annotation.*;
import pl.training.bank.BankException;
import pl.training.bank.disposition.Disposition;

@Aspect
public class ConsoleOperationLogger {

    private static final String SEPARATOR = "##################################################################################################";

    @Pointcut("execution(void pl.training.bank.disposition.DispositionService.process(..))")
    public void process() {
    }

    @Before("pl.training.bank.operation.ConsoleOperationLogger.process() && args(disposition)")
    public void onStart(Disposition disposition) {
        System.out.format("%s\n%s\n", SEPARATOR, disposition);
    }

    @AfterReturning("process()")
    public void onSuccess() {
        System.out.format("Status: SUCCESS\n%s\n", SEPARATOR);
    }

    @AfterThrowing(value = "process()", throwing = "ex")
    public void onException(BankException ex) {
        System.out.format("Status: EXCEPTION (%s)\n%s\n", ex.getClass().getSimpleName(), SEPARATOR);
    }

}
