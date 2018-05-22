package pl.training.bank.generator;

import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String ACCOUNT_NUMBER_FORMAT = "%026d";

    @Setter
    private AtomicLong counter = new AtomicLong();

    @Override
    public String getNext() {
        return String.format(ACCOUNT_NUMBER_FORMAT, counter.incrementAndGet());
    }

}
