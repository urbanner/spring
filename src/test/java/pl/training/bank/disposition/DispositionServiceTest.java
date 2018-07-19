package pl.training.bank.disposition;

import org.junit.Before;
import org.junit.Test;
import pl.training.bank.account.entity.Account;
import pl.training.bank.account.services.AccountNotFoundException;
import pl.training.bank.account.repository.AccountRepository;
import pl.training.bank.disposition.entity.Disposition;
import pl.training.bank.disposition.service.DispositionService;
import pl.training.bank.operation.entity.Operation;
import pl.training.bank.operation.service.UnknownOperationException;

import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.mockito.Mockito.*;

public class DispositionServiceTest {

    private Operation operation = mock(Operation.class);
    private Disposition disposition = new Disposition("00000000000000000000000001", 10_000, "deposit");
    private Map<String, Operation> operations = singletonMap("deposit", operation);
    private Account account = mock(Account.class);
    private AccountRepository accountRepository = mock(AccountRepository.class);

    @Before
    public void setUp() {
        when(accountRepository.getByNumber(anyString())).thenReturn(Optional.of(account));
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowExceptionWhenAccountNotFound() {
        DispositionService dispositionService = new DispositionService(mock(AccountRepository.class), operations);
        dispositionService.process(disposition);
    }

    @Test(expected = UnknownOperationException.class)
    public void shouldThrowExceptionWhenOperationIsUnknown() {
        DispositionService dispositionService = new DispositionService(accountRepository, emptyMap());
        dispositionService.process(disposition);
    }

    @Test
    public void shouldExecuteOperation() {
        DispositionService dispositionService = new DispositionService(accountRepository, operations);
        dispositionService.process(disposition);
        verify(operation).execute(account, disposition.getFunds());
    }


    @Test
    public void shouldUpdateAccount() {
        DispositionService dispositionService = new DispositionService(accountRepository, operations);
        dispositionService.process(disposition);
        verify(accountRepository).save(account);
    }

}
