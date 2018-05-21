package pl.training.bank.operation;

import pl.training.bank.account.Account;

public class DepositOperation implements Operation {

    @Override
    public void execute(Account account, long funds) {
        account.deposit(funds);
    }

}
