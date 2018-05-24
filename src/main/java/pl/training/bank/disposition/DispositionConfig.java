package pl.training.bank.disposition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.disposition.ConsoleDispositionLogger;
import pl.training.bank.disposition.DispositionService;
import pl.training.bank.operation.Operation;

import java.util.Map;

@Configuration
public class DispositionConfig {

    @Bean
    public DispositionService dispositionService(AccountRepository accountRepository, Map<String, Operation> operations) {
        return new DispositionService(accountRepository, operations);
    }

    @Bean
    public ConsoleDispositionLogger dispositionLogger() {
        return new ConsoleDispositionLogger();
    }

}
