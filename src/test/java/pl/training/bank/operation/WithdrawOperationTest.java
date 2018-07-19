package pl.training.bank.operation;

import org.junit.Before;
import org.junit.Test;
import pl.training.bank.account.entity.Account;
import pl.training.bank.operation.service.InsufficientFundsException;
import pl.training.bank.operation.service.WithdrawOperation;

import static org.mockito.Mockito.*;

public class WithdrawOperationTest {

    private static final long FUNDS = 1_000;

    private Account account = mock(Account.class);

    @Before
    public void setUp() {
        when(account.getBalance()).thenReturn(FUNDS);
    }

    @Test
    public void shouldDepositFundsOnAccount() {
        new WithdrawOperation().execute(account, FUNDS);
        verify(account).withdraw(FUNDS);
    }

    @Test(expected = InsufficientFundsException.class)
    public void shouldThrowExceptionWhenThereIsInsufficientFunds() {
        new WithdrawOperation().execute(account, FUNDS + 1);
    }

}
