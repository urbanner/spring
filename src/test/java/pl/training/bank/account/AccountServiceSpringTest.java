package pl.training.bank.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.training.bank.account.repository.AccountRepository;
import pl.training.bank.account.services.AccountNotFoundException;
import pl.training.bank.account.services.AccountService;
import pl.training.bank.generator.service.AccountNumberGenerator;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountServiceSpringTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        public AccountService accountService(AccountNumberGenerator accountNumberGenerator, AccountRepository accountRepository) {
            return new AccountService(accountNumberGenerator, accountRepository);
        }

    }

    @Autowired
    private AccountService accountService;
    @MockBean
    private AccountNumberGenerator accountNumberGenerator;
    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        when(accountRepository.getById(anyLong())).thenReturn(Optional.empty());
    }


    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowWhenUserNotFound() {
        accountService.getAccountById(1L);
    }

}
