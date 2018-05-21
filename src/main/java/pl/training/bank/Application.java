package pl.training.bank;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountService;
import pl.training.bank.config.Bank;
import pl.training.bank.disposition.Disposition;
import pl.training.bank.disposition.DispositionService;

public class Application {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Bank.class)) {
            AccountService accountService = context.getBean(AccountService.class);
            DispositionService dispositionService = context.getBean(DispositionService.class);

            Account account = accountService.createAccount();
            dispositionService.process(new Disposition(account.getNumber(), 10_000, "deposit"));
            dispositionService.process(new Disposition(account.getNumber(), 5_000, "withdraw"));
            System.out.println(accountService.getAccounts(0, 10).getData());
        }
    }

}
