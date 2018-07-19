package pl.training.bank.operation.entity;

import pl.training.bank.account.entity.Account;

public interface Operation {

    void execute(Account account, long funds);

}
