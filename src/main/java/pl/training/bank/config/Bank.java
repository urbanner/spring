package pl.training.bank.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import pl.training.bank.common.BeanLoggerPostProcessor;
import pl.training.bank.common.ContextListener;
import pl.training.bank.common.Profiler;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties")
@Import({Account.class, Disposition.class, Operation.class})
@EnableAspectJAutoProxy
@Configuration
public class Bank {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setJdbcUrl(environment.getProperty("database.url"));
        dataSource.setDriverClassName(environment.getProperty("database.driver"));
        dataSource.setMaximumPoolSize(Integer.parseInt(environment.getProperty("database.max-pool-size")));
        return dataSource;
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
