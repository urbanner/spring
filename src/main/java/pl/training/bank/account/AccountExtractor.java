package pl.training.bank.account;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountExtractor implements ResultSetExtractor<Optional<Account>> {

    private static final String ID_COLUMN = "id";
    private static final String NUMBER_COLUMN = "account_number";
    private static final String BALANCE_COLUMN = "balance";

    @Override
    public Optional<Account> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (resultSet.next()) {
            Account account = new Account(resultSet.getString(NUMBER_COLUMN));
            account.setId(resultSet.getLong(ID_COLUMN));
            account.setBalance(resultSet.getLong(BALANCE_COLUMN));
            return Optional.of(account);
        }
        return Optional.empty();
    }

}
