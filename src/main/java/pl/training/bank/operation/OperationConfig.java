package pl.training.bank.operation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.account.repository.AccountRepository;
import pl.training.bank.operation.service.DepositOperation;
import pl.training.bank.operation.event.LargeDepositListener;
import pl.training.bank.operation.aop.LargeDepositLogger;
import pl.training.bank.operation.service.WithdrawOperation;
import pl.training.bank.operation.aop.OperationHistoryLogger;
import pl.training.bank.operation.service.OperationHistoryRepository;

@Configuration
public class OperationConfig {

    @Bean
    public DepositOperation deposit() {
        return new DepositOperation();
    }

    @Bean
    public WithdrawOperation withdraw() {
        return new WithdrawOperation();
    }

    @Bean
    public LargeDepositLogger largeDepositLogger() {
        return new LargeDepositLogger();
    }

    @Bean
    public LargeDepositListener largeDepositListener() {
        return new LargeDepositListener();
    }

    @Bean
    public OperationHistoryLogger operationHistoryLogger(OperationHistoryRepository operationHistoryRepository, AccountRepository accountRepository) {
        return new OperationHistoryLogger(operationHistoryRepository, accountRepository);
    }

}
