package pl.training.bank.account;

import lombok.Setter;
import pl.training.bank.common.ResultPage;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class JpaAccountRepository implements AccountRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

    @Override
    public ResultPage<Account> get(int pageNumber, int pageSize) {
        return new ResultPage<>(entityManager.createNamedQuery(Account.SELECT_ACCOUNTS, Account.class)
            .setFirstResult(pageNumber * pageSize)
            .setMaxResults(pageNumber)
            .getResultList(), pageNumber, 0);
    }

    @Override
    public Optional<Account> getByNumber(String accountNumber) {
        try {
            return Optional.of(entityManager.createNamedQuery(Account.SELECT_ACCOUNT_BY_NUMBER, Account.class)
                    .setParameter("number", accountNumber)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Account account) {
        if (entityManager.find(Account.class, account.getId()) == null) {
            throw new AccountNotFoundException();
        }
        entityManager.merge(account);
    }

}
