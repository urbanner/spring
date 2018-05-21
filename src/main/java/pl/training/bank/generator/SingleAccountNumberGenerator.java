package pl.training.bank.generator;

import org.springframework.stereotype.Service;

@Generator(type = "single")
//@Service
public class SingleAccountNumberGenerator implements AccountNumberGenerator {

    private String accountNumber = "00000000000000000000000001";

    @Override
    public String getNext() {
        return accountNumber;
    }

}
