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
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accounts")
@Entity
public class Account {
    public static final String SELECT_BY_NUMBER = "selectaccountByNumber";
    public static final String SELECT_ALL = "selectAllAccounts";

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(name = "account_number", unique = true)
    private String number;
    private long balance;

    public void deposit(long funds) {
        balance += funds;
    }

    public void withdraw(long funds) {
        balance -= funds;
    }

}
