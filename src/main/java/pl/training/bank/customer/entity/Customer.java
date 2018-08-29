package pl.training.bank.customer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.training.bank.account.entity.Account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Customer {

    @GeneratedValue
    @Id
    private Long id;

    @NonNull
    private String name;

    @ManyToMany
    private List<Account> accounts = new ArrayList<>();
}
