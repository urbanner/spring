package pl.training.bank.account;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.training.bank.common.ResultPage;
import pl.training.bank.generator.AccountNumberGenerator;
import pl.training.bank.generator.Generator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Log
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class AccountService {

    private AccountNumberGenerator accountNumberGenerator;
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(@Generator(type = "incremental")
                          //@Qualifier("singleAccountNumberGenerator")
                          AccountNumberGenerator accountNumberGenerator,
                          AccountRepository accountRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.accountRepository = accountRepository;
    }

    public Account createAccount() {
        String accountNumber = accountNumberGenerator.getNext();
        Account account = new Account(accountNumber);
        return accountRepository.save(account);
    }

    public ResultPage<Account> getAccounts(int pageNumber, int pageSize) {
        return accountRepository.get(pageNumber, pageSize);
    }

    @PostConstruct
    public void init() {
        log.info("### Initializing: " + getClass().getSimpleName());
    }

    @PreDestroy
    public void destroy() {
        log.info("### Closing: " + getClass().getSimpleName());
    }

}
