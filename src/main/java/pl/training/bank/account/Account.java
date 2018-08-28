package pl.training.bank.account;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Account.SELECT_BY_NUMBER, query = "select a from Account a where a.number = :number"),
        @NamedQuery(name = Account.SELECT_ALL, query = "select a from Account a")
})
@Table(name = "accounts")
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    public static final String SELECT_BY_NUMBER = "selectAccountByNumber";
    public static final String SELECT_ALL = "selectAllAccounts";

    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "account_number", unique = true)
    @NonNull
    private String number;
    private long balance;

    public void deposit(long funds) {
        balance += funds;
    }

    public void withdraw(long funds) {
        balance -= funds;
    }

}
