package pl.training.bank.operation.history;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import pl.training.bank.disposition.Disposition;

import java.util.Date;

@Aspect
@RequiredArgsConstructor
public class OperationHistoryLogger {

    @NonNull
    private OperationHistoryRepository operationHistoryRepository;

    @AfterReturning("execution(void pl.training.bank.disposition.DispositionService.process(..)) && args(disposition)")
    public void onDeposit(Disposition disposition) {
        OperationHistoryEntry entry = new OperationHistoryEntry();
        entry.setAccountNumber(disposition.getAccountNumber());
        entry.setDate(new Date());
        entry.setFunds(disposition.getFunds());
        entry.setType(disposition.getOperationName());
        operationHistoryRepository.save(entry);
    }

}
