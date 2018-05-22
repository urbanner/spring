package pl.training.bank.generator;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

public class JdbcTemplateIncrementalAccountNumberGenerator extends IncrementalAccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(account_number) from account";

    public JdbcTemplateIncrementalAccountNumberGenerator(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String lastAccountNumber = jdbcTemplate.queryForObject(SELECT_LAST_ACCOUNT_NUMBER, String.class);
        if (lastAccountNumber != null) {
            setCounter(new AtomicLong(Long.parseLong(lastAccountNumber)));
        }
    }

}
