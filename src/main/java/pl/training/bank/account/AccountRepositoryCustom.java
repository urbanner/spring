package pl.training.bank.account;

import java.util.Optional;

public interface AccountRepositoryCustom {

    Optional<Account> getByNumber(String accountNumber);

}
