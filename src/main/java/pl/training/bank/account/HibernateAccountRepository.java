package pl.training.bank.account;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.training.bank.common.ResultPage;

import java.util.Optional;

@RequiredArgsConstructor
public class HibernateAccountRepository implements AccountRepository {

    private static final String SELECT_ACCOUNT_BY_NUMBER = "select a from Account a where a.number = :number";
    private static final String SELECT_ACCOUNTS = "select a from Account a";

    @NonNull
    private SessionFactory sessionFactory;

    @Override
    public Account save(Account account) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(account);
        account.setId(id);
        return account;
    }

    @Override
    public ResultPage<Account> get(int pageNumber, int pageSize) {
        return new ResultPage<>(sessionFactory.getCurrentSession()
                .createQuery(SELECT_ACCOUNTS, Account.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList(), pageNumber, 0);
    }

    @Override
    public Optional<Account> getByNumber(String accountNumber) {
        return sessionFactory.getCurrentSession()
                .createQuery(SELECT_ACCOUNT_BY_NUMBER, Account.class)
                .setParameter("number", accountNumber)
                .uniqueResultOptional();
    }

    @Override
    public void update(Account account) {
        Session session = sessionFactory.getCurrentSession();
        if (session.load(Account.class, account.getId()) == null) {
            throw new AccountNotFoundException();
        }
        session.update(account);
    }

}
