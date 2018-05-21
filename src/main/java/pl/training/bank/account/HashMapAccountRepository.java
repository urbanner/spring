package pl.training.bank.account;

import pl.training.bank.common.ResultPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapAccountRepository implements AccountRepository {

    private Map<String, Account> accounts = new HashMap<>();
    private long counter;

    @Override
    public Account save(Account account) {
        account.setId(++counter);
        accounts.put(account.getNumber(), account);
        return account;
    }

    @Override
    public ResultPage<Account> get(int pageNumber, int pageSize) {
        return new ResultPage<>(new ArrayList<>(accounts.values()));
    }

    @Override
    public Optional<Account> getByNumber(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    @Override
    public void update(Account account) {
        String accountNumber = account.getNumber();
        if (accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, account);
        }
    }

}
