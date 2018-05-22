package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.account.AccountService;
import pl.training.bank.account.JpaAccountRepository;
import pl.training.bank.generator.AccountNumberGenerator;
import pl.training.bank.generator.JpaIncrementalAccountNumberGenerator;

import javax.persistence.EntityManagerFactory;

@Configuration
public class Account {

    @Bean
    public AccountNumberGenerator accountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        return new JpaIncrementalAccountNumberGenerator(entityManagerFactory);
    }

    @Bean
    public AccountRepository accountRepository() {
        return new JpaAccountRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AccountService accountService(AccountNumberGenerator accountNumberGenerator, AccountRepository accountRepository) {
        return new AccountService(accountNumberGenerator, accountRepository);
    }

}
