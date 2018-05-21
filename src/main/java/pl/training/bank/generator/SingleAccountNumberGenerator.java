package pl.training.bank.generator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SingleAccountNumberGenerator implements AccountNumberGenerator {

    @NonNull
    private String accountNumber;

    @Override
    public String getNext() {
        return accountNumber;
    }

}
