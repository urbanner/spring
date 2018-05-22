package pl.training.bank.account;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsListExtractor implements ResultSetExtractor<List<Account>> {

    private static final String ID_COLUMN = "id";
    private static final String NUMBER_COLUMN = "account_number";
    private static final String BALANCE_COLUMN = "balance";

    @Override
    public List<Account> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            Account account = new Account(resultSet.getString(NUMBER_COLUMN));
            account.setId(resultSet.getLong(ID_COLUMN));
            account.setBalance(resultSet.getLong(BALANCE_COLUMN));
            accounts.add(account);
        }
        return accounts;
    }

}
