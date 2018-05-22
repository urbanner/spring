package pl.training.bank.account;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import pl.training.bank.common.ResultPage;
import pl.training.bank.generator.AccountNumberGenerator;

@Transactional
@Log
@AllArgsConstructor
public class AccountService {

    private AccountNumberGenerator accountNumberGenerator;
    private AccountRepository accountRepository;

    public Account createAccount() {
        String accountNumber = accountNumberGenerator.getNext();
        Account account = new Account(accountNumber);
        return accountRepository.save(account);
    }

    public ResultPage<Account> getAccounts(int pageNumber, int pageSize) {
        Page<Account> result = accountRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new ResultPage<>(result.getContent(), pageNumber, result.getTotalPages());
    }

    public void init() {
        log.info("### Initializing: " + getClass().getSimpleName());
    }

    public void destroy() {
        log.info("### Closing: " + getClass().getSimpleName());
    }

}
