package pl.training.bank.generator;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Generator(type = "incremental")
//@Service("accountNumberGenerator")
public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String ACCOUNT_NUMBER_FORMAT = "%026d";

    private AtomicLong counter = new AtomicLong();

    @Override
    public String getNext() {
        return String.format(ACCOUNT_NUMBER_FORMAT, counter.incrementAndGet());
    }

}
