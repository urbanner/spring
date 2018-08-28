package pl.training.bank.generator;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.atomic.AtomicLong;

public class JpaIncrementalAccountNumberGenerator extends IncrementalAccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER  = "select max(a.number) from Account a";
    private AtomicLong counter;

    public JpaIncrementalAccountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        String lastCounter = entityManagerFactory.createEntityManager()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER, String.class)
                .getSingleResult();
        if (lastCounter != null) {
            setCounter(new AtomicLong(Long.parseLong(lastCounter)));
        }
    }
}
