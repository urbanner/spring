package pl.training.bank.operation.history;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountService;
import pl.training.bank.disposition.Disposition;

import java.util.Date;
import java.util.Optional;

@Aspect
@RequiredArgsConstructor
public class JpaHistoryLogger {

    @NonNull
    private HistoryRepository historyRepository;
    @NonNull
    private AccountService accountService;

    @AfterReturning("execution(void pl.training.bank.disposition.DispositionService.process(..)) && args(disposition)")
    public void addHistoryEntry(Disposition disposition) {
        accountService.getByNumber(disposition.getAccountNumber())
                .ifPresent(account -> addEntry(account, disposition));
    }

    private void addEntry(Account account, Disposition disposition) {
        historyRepository.addEntry(new HistoryEntry.HistoryEntryBuilder()
                .account(account)
                .operationName(disposition.getOperationName())
                .funds(disposition.getFunds())
                .timestamp(new Date())
                .build()
        );
    }

}
