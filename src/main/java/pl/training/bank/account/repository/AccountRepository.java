package pl.training.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.training.bank.account.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

    Optional<Account> getById(Long id);

    @Query("select a from Account a where a.balance >= :balance")
    List<Account> getAccountsWithBalance(@Param("balance") long balance);

}
