package pl.training.bank.operation;

import pl.training.bank.account.Account;

public interface Operation {

    void execute(Account account, long funds);

}
