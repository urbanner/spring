package pl.training.bank.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.account.repository.AccountRepository;
import pl.training.bank.account.services.AccountService;
import pl.training.bank.customer.repository.CustomerRepository;
import pl.training.bank.customer.services.CustomerService;
import pl.training.bank.generator.service.AccountNumberGenerator;
import pl.training.bank.generator.service.JpaIncrementalAccountNumberGenerator;

import javax.persistence.EntityManagerFactory;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository, AccountService accountService) {
        return new CustomerService(customerRepository, accountService);
    }

}