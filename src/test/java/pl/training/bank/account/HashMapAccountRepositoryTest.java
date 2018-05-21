package pl.training.bank.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HashMapAccountRepositoryTest {

    private HashMapAccountRepository accountRepository = new HashMapAccountRepository();
    private Account account = mock(Account.class);

    @DisplayName("Should assign an id to saved account")
    @Test
    void shouldAssignIdToSavedAccount() {
        accountRepository.save(account);
        verify(account).setId(anyLong());
    }

}
