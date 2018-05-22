package pl.training.bank.generator;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

public class HibernateIncrementalAccountNumberGenerator extends IncrementalAccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    public HibernateIncrementalAccountNumberGenerator(SessionFactory sessionFactory) {
        String lastAccountNumber = sessionFactory.openSession()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER, String.class)
                .uniqueResult();
        if (lastAccountNumber != null) {
            setCounter(new AtomicLong(Long.parseLong(lastAccountNumber)));
        }
    }

}
