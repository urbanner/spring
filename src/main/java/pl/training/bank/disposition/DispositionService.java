package pl.training.bank.disposition;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountNotFoundException;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.UnknownOperationException;

import java.util.Map;

@Service
@AllArgsConstructor
public class DispositionService {

    private AccountRepository accountRepository;
    private Map<String, Operation> operations;

    public void process(Disposition disposition) {
        Account account = getAccount(disposition.getAccountNumber());
        Operation operation = getOperation(disposition.getOperationName());
        operation.execute(account, disposition.getFunds());
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
