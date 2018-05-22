package pl.training.bank.operation.history;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountNotFoundException;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.disposition.Disposition;

import java.util.Date;

@Aspect
@RequiredArgsConstructor
public class OperationHistoryLogger {

    @NonNull
    private OperationHistoryRepository operationHistoryRepository;
    @NonNull
    private AccountRepository accountRepository;

    @AfterReturning("execution(void pl.training.bank.disposition.DispositionService.process(..)) && args(disposition)")
    public void logOperation(Disposition disposition) {
        Account account = accountRepository.getByNumber(disposition.getAccountNumber())
                .orElseThrow(AccountNotFoundException::new);
        OperationHistoryEntry entry = new OperationHistoryEntry();
        entry.setAccount(account);
        entry.setDate(new Date());
        entry.setFunds(disposition.getFunds());
        entry.setType(disposition.getOperationName());
        operationHistoryRepository.save(entry);
    }

}
