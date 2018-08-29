package pl.training.bank.account;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pl.training.bank.account.entity.Account;
import pl.training.bank.account.repository.AccountRepository;
import pl.training.bank.account.services.AccountService;
import pl.training.bank.common.aop.ResultPage;
import pl.training.bank.generator.service.AccountNumberGenerator;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private static final String ACCOUNT_NUMBER = "00000000000000000000000001";

    private AccountNumberGenerator accountNumberGenerator = mock(AccountNumberGenerator.class);
    private AccountRepository accountRepository = mock(AccountRepository.class);
    private AccountService accountService = new AccountService(accountNumberGenerator, accountRepository);
    private ResultPage<Account> resultPage = new ResultPage<>(new ArrayList<>());

    @Before
    public void setUp() {
        when(accountNumberGenerator.getNext()).thenReturn(ACCOUNT_NUMBER);
        when(accountRepository.getById(anyLong())).thenReturn(Optional.of(new Account()));
        when(accountRepository.save(any(Account.class))).then(returnsFirstArg());
        when(accountRepository.findAll(PageRequest.of(0, 1))).thenReturn(new PageImpl<>(new ArrayList<>()));
        resultPage.setTotalPages(1);
    }

    @Test
    public void shouldSaveCreatedAccount() {
        Account account = accountService.createAccount();
        verify(accountRepository).save(account);
    }

    @Test
    public void shouldAssignNumberToCreatedAccount() {
        Account account = accountService.createAccount();
        assertEquals(ACCOUNT_NUMBER, account.getNumber());
    }

    @Test
    public void shouldReturnPageOfAccounts() {
        assertEquals(resultPage, accountService.getAccounts(0, 1));
    }


    @Test
    public void shouldDeleteAccount(){
        accountService.deleteAccount(1L);
        verify(accountRepository).delete(any(Account.class));
    }
}
