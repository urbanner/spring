package pl.training.bank.disposition;

import pl.training.bank.BankException;

public class ConsoleDispositionLogger {

    private static final String SEPARATOR = "##################################################################################################";

    public void onStart(Disposition disposition) {
        System.out.format("%s\n%s\n", SEPARATOR, disposition);
    }

    public void onSuccess() {
        System.out.format("Status: SUCCESS\n%s\n", SEPARATOR);
    }

    public void onException(BankException ex) {
        System.out.format("Status: EXCEPTION (%s)\n%s\n", ex.getClass().getSimpleName(), SEPARATOR);
    }

}
