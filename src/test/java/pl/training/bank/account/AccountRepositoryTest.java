package pl.training.bank.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.training.bank.account.entity.Account;
import pl.training.bank.account.repository.AccountRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {

    private static final long FUNDS = 1_000;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TestEntityManager entityManager;
    private Account account = new Account();

    @Before
    public void setUp() {
        account.setBalance(FUNDS);
        entityManager.persist(account);
        entityManager.persist(new Account());
        entityManager.flush();
    }

    @Test
    public void shouldReturnAccountsWithExpectedBalance() {
        List<Account> accounts = accountRepository.getAccountsWithBalance(FUNDS);
        assertTrue(accounts.contains(account));
        assertEquals(1, accounts.size());
    }

}
