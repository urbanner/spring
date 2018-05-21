package pl.training.bank.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.training.bank.account.Account;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WithdrawOperationTest {

    private static final long FUNDS = 1_000;

    private Account account = mock(Account.class);

    @BeforeEach
    void setUp() {
        when(account.getBalance()).thenReturn(FUNDS);
    }

    @DisplayName("Should withdraw funds from account")
    @Test
    void shouldDepositFundsOnAccount() {
        new WithdrawOperation().execute(account, FUNDS);
        verify(account).withdraw(FUNDS);
    }

    @DisplayName("Should throw exception when there is insufficient funds")
    @Test
    void shouldThrowExceptionWhenThereIsInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, () -> new WithdrawOperation().execute(account, FUNDS + 1));
    }

}
