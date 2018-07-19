package pl.training.bank.operation.service;

import pl.training.bank.account.entity.Account;
import pl.training.bank.operation.entity.Operation;

public class WithdrawOperation implements Operation {

    @Override
    public void execute(Account account, long funds) {
        if (account.getBalance() < funds) {
            throw new InsufficientFundsException();
        }
        account.withdraw(funds);
    }

}
