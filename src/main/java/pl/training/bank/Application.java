package pl.training.bank;

import pl.training.bank.account.Account;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.account.AccountService;
import pl.training.bank.account.HashMapAccountRepository;
import pl.training.bank.disposition.Disposition;
import pl.training.bank.disposition.DispositionService;
import pl.training.bank.generator.AccountNumberGenerator;
import pl.training.bank.generator.IncrementalAccountNumberGenerator;
import pl.training.bank.operation.DepositOperation;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.WithdrawOperation;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        AccountRepository accountRepository = new HashMapAccountRepository();
        AccountNumberGenerator accountNumberGenerator = new IncrementalAccountNumberGenerator();
        AccountService accountService = new AccountService(accountNumberGenerator, accountRepository);

        Map<String, Operation> operations = new HashMap<>();
        operations.put("deposit", new DepositOperation());
        operations.put("withdraw", new WithdrawOperation());
        DispositionService dispositionService = new DispositionService(accountRepository, operations);

        Account account = accountService.createAccount();
        dispositionService.process(new Disposition(account.getNumber(), 10_000, "deposit"));
        dispositionService.process(new Disposition(account.getNumber(), 5_000, "withdraw"));
        System.out.println(accountService.getAccounts(0,10).getData());
    }

}
