package pl.training.bank.account.repository;

import pl.training.bank.account.entity.Account;

import java.util.Optional;

public interface AccountRepositoryCustom {

    Optional<Account> getByNumber(String accountNumber);

}
