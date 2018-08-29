package pl.training.bank.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.bank.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
