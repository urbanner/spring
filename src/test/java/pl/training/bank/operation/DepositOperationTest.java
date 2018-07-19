package pl.training.bank.operation;

import org.junit.Test;
import pl.training.bank.account.entity.Account;
import pl.training.bank.operation.service.DepositOperation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DepositOperationTest {

    private static final long FUNDS = 1_000;

    private Account account = mock(Account.class);

    @Test
    public void shouldDepositFundsOnAccount() {
        new DepositOperation().execute(account, FUNDS);
        verify(account).deposit(FUNDS);
    }

}
