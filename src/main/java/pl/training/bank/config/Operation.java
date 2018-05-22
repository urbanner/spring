package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.operation.DepositOperation;
import pl.training.bank.operation.LargeDepositListener;
import pl.training.bank.operation.LargeDepositLogger;
import pl.training.bank.operation.WithdrawOperation;
import pl.training.bank.operation.history.OperationHistoryLogger;
import pl.training.bank.operation.history.OperationHistoryRepository;

@Configuration
public class Operation {

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
    public OperationHistoryLogger operationHistoryLogger(OperationHistoryRepository operationHistoryRepository) {
        return new OperationHistoryLogger(operationHistoryRepository);
    }

}
