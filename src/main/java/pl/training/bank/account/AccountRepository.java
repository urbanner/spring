package pl.training.bank.account;

import pl.training.bank.common.ResultPage;

import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    ResultPage<Account> get(int pageNumber, int pageSize);

    Optional<Account> getByNumber(String accountNumber);

    void update(Account account);

}
