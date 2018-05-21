package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.account.AccountService;
import pl.training.bank.account.HashMapAccountRepository;
import pl.training.bank.generator.AccountNumberGenerator;
import pl.training.bank.generator.IncrementalAccountNumberGenerator;

@Configuration
public class Account {

    @Bean
    public AccountNumberGenerator accountNumberGenerator() {
        return new IncrementalAccountNumberGenerator();
    }

    @Bean
    public AccountRepository accountRepository() {
        return new HashMapAccountRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AccountService accountService(AccountNumberGenerator accountNumberGenerator, AccountRepository accountRepository) {
        return new AccountService(accountNumberGenerator, accountRepository);
    }

}
