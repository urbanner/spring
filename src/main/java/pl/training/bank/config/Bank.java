package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import pl.training.bank.common.BeanLoggerPostProcessor;
import pl.training.bank.common.ContextListener;
import pl.training.bank.common.Profiler;

@Import({Account.class, Disposition.class, Operation.class})
@EnableAspectJAutoProxy
@Configuration
public class Bank {

    @Bean
    public BeanLoggerPostProcessor beanLogger() {
        return new BeanLoggerPostProcessor();
    }

    @Bean
    public Profiler profiler() {
        return new Profiler();
    }

    @Bean
    public ContextListener contextListener() {
        return new ContextListener();
    }

    @Bean
    public ApplicationEventMulticaster eventMulticaster() {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return multicaster;
    }

}
