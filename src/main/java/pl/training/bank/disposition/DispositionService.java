package pl.training.bank.disposition;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountNotFoundException;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.common.ExecutionTime;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.UnknownOperationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;
@Transactional
@RequiredArgsConstructor
public class DispositionService {

    @NonNull
    private AccountRepository accountRepository;
    @NonNull
    private Map<String, Operation> operations;
    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Disposition disposition) {
        entityManager.persist(disposition);
        entityManager.flush();
        entityManager.refresh(disposition);
    }

    @ExecutionTime
    public void process(Disposition disposition) {
        Account account = getAccount(disposition.getAccountNumber());
        Operation operation = getOperation(disposition.getOperationName());
        operation.execute(account, disposition.getFunds());
        save(disposition);
        accountRepository.update(account);
    }

    private Account getAccount(String accountNumber) {
        return accountRepository.getByNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new);
    }

    private Operation getOperation(String operationName) {
        if (operationName == null || !operations.containsKey(operationName)) {
            throw new UnknownOperationException();
        }
        return operations.get(operationName);
    }

}
