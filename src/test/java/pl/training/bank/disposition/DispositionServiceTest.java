package pl.training.bank.disposition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountNotFoundException;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.UnknownOperationException;

import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DispositionServiceTest {

    private Operation operation = mock(Operation.class);
    private Disposition disposition = new Disposition("00000000000000000000000001", 10_000, "deposit");
    private Map<String, Operation> operations = singletonMap("deposit", operation);
    private Account account = mock(Account.class);
    private AccountRepository accountRepository = mock(AccountRepository.class);

//    @BeforeEach
//    void setUp() {
//        when(accountRepository.getByNumber(anyString())).thenReturn(Optional.of(account));
//    }
//
//    @DisplayName("Should throw exception when account not found")
//    @Test
//    void shouldThrowExceptionWhenAccountNotFound() {
//        DispositionService dispositionService = new DispositionService(mock(AccountRepository.class), operations);
//        assertThrows(AccountNotFoundException.class, () -> dispositionService.process(disposition));
//    }
//
//    @DisplayName("Should throw exception when operation is unknown")
//    @Test
//    void shouldThrowExceptionWhenOperationIsUnknown() {
//        DispositionService dispositionService = new DispositionService(accountRepository, emptyMap());
//        assertThrows(UnknownOperationException.class, () -> dispositionService.process(disposition));
//    }
//
//    @DisplayName("Should execute operation")
//    @Test
//    void shouldExecuteOperation() {
//        DispositionService dispositionService = new DispositionService(accountRepository, operations);
//        dispositionService.process(disposition);
//        verify(operation).execute(account, disposition.getFunds());
//    }
//
//
//    @DisplayName("Should update account")
//    @Test
//    void shouldUpdateAccount() {
//        DispositionService dispositionService = new DispositionService(accountRepository, operations);
//        dispositionService.process(disposition);
//        verify(accountRepository).update(account);
//    }

}
