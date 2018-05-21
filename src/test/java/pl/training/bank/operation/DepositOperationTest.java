package pl.training.bank.operation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.training.bank.account.Account;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DepositOperationTest {

    private static final long FUNDS = 1_000;

    private Account account = mock(Account.class);

    @DisplayName("Should deposit funds on account")
    @Test
    void shouldDepositFundsOnAccount() {
        new DepositOperation().execute(account, FUNDS);
        verify(account).deposit(FUNDS);
    }

}
