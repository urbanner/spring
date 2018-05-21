package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.training.bank.account.AccountRepository;
import pl.training.bank.account.AccountService;
import pl.training.bank.account.HashMapAccountRepository;
import pl.training.bank.common.BeanLoggerPostProcessor;
import pl.training.bank.common.Profiler;
import pl.training.bank.disposition.DispositionService;
import pl.training.bank.generator.AccountNumberGenerator;
import pl.training.bank.generator.IncrementalAccountNumberGenerator;
import pl.training.bank.disposition.ConsoleDispositionLogger;
import pl.training.bank.operation.DepositOperation;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.WithdrawOperation;

import java.util.Map;

@EnableAspectJAutoProxy
@Configuration
public class Beans {

    @Bean
    public AccountNumberGenerator accountNumberGenerator() {
        return new IncrementalAccountNumberGenerator();
    }

    @Bean
    public AccountRepository accountRepository() {
        return new HashMapAccountRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AccountService accountService(AccountNumberGenerator accountNumberGenerator, AccountRepository accountRepository) {
        return new AccountService(accountNumberGenerator, accountRepository);
    }

    @Bean
    public DepositOperation deposit() {
        return new DepositOperation();
    }

    @Bean
    public WithdrawOperation withdraw() {
        return new WithdrawOperation();
    }

    @Bean
    public DispositionService dispositionService(AccountRepository accountRepository, Map<String, Operation> operations) {
        return new DispositionService(accountRepository, operations);
    }

    @Bean
    public BeanLoggerPostProcessor beanLogger() {
        return new BeanLoggerPostProcessor();
    }

    @Bean
    public Profiler profiler() {
        return new Profiler();
    }

    @Bean
    public ConsoleDispositionLogger dispositionLogger() {
        return new ConsoleDispositionLogger();
    }

}
