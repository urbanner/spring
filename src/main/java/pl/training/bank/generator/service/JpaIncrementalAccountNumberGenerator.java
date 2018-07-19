package pl.training.bank.generator.service;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.atomic.AtomicLong;

public class JpaIncrementalAccountNumberGenerator extends IncrementalAccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    public JpaIncrementalAccountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        String lastAccountNumber = entityManagerFactory.createEntityManager()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER, String.class)
                .getSingleResult();
        if (lastAccountNumber != null) {
            setCounter(new AtomicLong(Long.parseLong(lastAccountNumber)));
        }
    }

}
