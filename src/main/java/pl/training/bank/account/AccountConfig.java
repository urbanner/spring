package pl.training.bank.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.account.repository.AccountRepository;
import pl.training.bank.account.services.AccountService;
import pl.training.bank.generator.service.AccountNumberGenerator;
import pl.training.bank.generator.service.JpaIncrementalAccountNumberGenerator;

import javax.persistence.EntityManagerFactory;

@Configuration
public class AccountConfig {

    @Bean
    public AccountNumberGenerator accountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        return new JpaIncrementalAccountNumberGenerator(entityManagerFactory);
    }

    @Bean
    public AccountService accountService(AccountNumberGenerator accountNumberGenerator, AccountRepository accountRepository) {
        return new AccountService(accountNumberGenerator, accountRepository);
    }

}
