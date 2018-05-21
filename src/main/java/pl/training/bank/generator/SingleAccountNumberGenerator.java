package pl.training.bank.generator;

public class SingleAccountNumberGenerator implements AccountNumberGenerator {

    private String accountNumber = "00000000000000000000000001";

    @Override
    public String getNext() {
        return accountNumber;
    }

}
