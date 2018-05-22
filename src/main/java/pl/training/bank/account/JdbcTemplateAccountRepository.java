package pl.training.bank.account;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.training.bank.common.ResultPage;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateAccountRepository implements AccountRepository {

    private static final String INSERT_ACCOUNT = "insert into account (account_number,balance) values (:number,:balance)";
    private static final String UPDATE_ACCOUNT = "update account set balance = :balance where account_number = :number";
    private static final String SELECT_ACCOUNT_BY_NUMBER = "select * from account where account_number = :number";
    private static final String SELECT_ACCOUNTS = "select * from account offset :start limit :limit";
    private static final int NO_RESULT = 0;

    private NamedParameterJdbcTemplate jdbcTemplate;
    private AccountExtractor accountExtractor = new AccountExtractor();
    private AccountsListExtractor accountsListExtractor = new AccountsListExtractor();

    public JdbcTemplateAccountRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Account save(Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_ACCOUNT, new BeanPropertySqlParameterSource(account), keyHolder);
        Long id = ((Integer) keyHolder.getKeys().get("id")).longValue();
        account.setId(id);
        return account;
    }

    @Override
    public ResultPage<Account> get(int pageNumber, int pageSize) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("start", pageNumber * pageSize)
                .addValue("limit", pageSize);
        List<Account> accounts = jdbcTemplate.query(SELECT_ACCOUNTS, parameters, accountsListExtractor);
        return new ResultPage<>(accounts, pageNumber, 0);
    }

    @Override
    public Optional<Account> getByNumber(String accountNumber) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("number", accountNumber);
        return jdbcTemplate.query(SELECT_ACCOUNT_BY_NUMBER, parameters, accountExtractor);
    }

    @Override
    public void update(Account account) {
        if (NO_RESULT == jdbcTemplate.update(UPDATE_ACCOUNT, new BeanPropertySqlParameterSource(account))) {
            throw new AccountNotFoundException();
        }
    }

}
