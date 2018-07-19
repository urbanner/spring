package pl.training.bank.operation.aop;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import pl.training.bank.account.entity.Account;
import pl.training.bank.account.services.AccountNotFoundException;
import pl.training.bank.account.repository.AccountRepository;
import pl.training.bank.disposition.entity.Disposition;
import pl.training.bank.operation.service.OperationHistoryEntry;
import pl.training.bank.operation.service.OperationHistoryRepository;

import java.util.Date;

@Aspect
@RequiredArgsConstructor
public class OperationHistoryLogger {

    @NonNull
    private OperationHistoryRepository operationHistoryRepository;
    @NonNull
    private AccountRepository accountRepository;

    @AfterReturning("execution(void pl.training.bank.disposition.service.DispositionService.process(..)) && args(disposition)")
    public void logOperation(Disposition disposition) {
        Account account = getAccount(disposition.getAccountNumber());
        OperationHistoryEntry historyEntry = createHistoryEntry(disposition, account);
        operationHistoryRepository.save(historyEntry);
    }

    private Account getAccount(String accountNumber) {
        return accountRepository.getByNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new);
    }

    private OperationHistoryEntry createHistoryEntry(Disposition disposition, Account account) {
        OperationHistoryEntry historyEntry = new OperationHistoryEntry();
        historyEntry.setAccount(account);
        historyEntry.setDate(new Date());
        historyEntry.setFunds(disposition.getFunds());
        historyEntry.setType(disposition.getOperationName());
        return historyEntry;
    }

}
