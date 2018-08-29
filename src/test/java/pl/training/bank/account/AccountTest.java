package pl.training.bank.account;

import org.junit.Test;
import pl.training.bank.account.entity.Account;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private static final long FUNDS = 1_000;
    private static final long NEGATIVE_FUNDS = -5_000;

    private Account account = new Account();
    private long initialBalance = account.getBalance();

    @Test
    public void shouldIncreaseBalanceAfterDeposit() {
        account.deposit(FUNDS);
        assertEquals(initialBalance + FUNDS, account.getBalance());
    }

    @Test
    public void shouldDecreaseBalanceAfterWithdraw() {
        account.withdraw(FUNDS);
        assertEquals(initialBalance - FUNDS, account.getBalance());
    }

    @Test
    public void shouldReturnError() {
        account.withdraw(NEGATIVE_FUNDS);
        assertEquals(initialBalance, account.getBalance());
    }

}
