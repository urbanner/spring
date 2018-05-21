package pl.training.bank.operation;

import org.springframework.stereotype.Component;
import pl.training.bank.account.Account;

@Component("withdraw")
public class WithdrawOperation implements Operation {

    @Override
    public void execute(Account account, long funds) {
        if (account.getBalance() < funds) {
            throw new InsufficientFundsException();
        }
        account.withdraw(funds);
    }

}
