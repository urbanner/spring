package pl.training.bank.operation;

import org.springframework.stereotype.Component;
import pl.training.bank.account.Account;

@Component("deposit")
public class DepositOperation implements Operation {

    @Override
    public void execute(Account account, long funds) {
        account.deposit(funds);
    }

}
