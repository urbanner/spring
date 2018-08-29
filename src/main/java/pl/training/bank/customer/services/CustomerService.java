package pl.training.bank.customer.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.training.bank.account.entity.Account;
import pl.training.bank.account.services.AccountService;
import pl.training.bank.common.aop.ResultPage;
import pl.training.bank.customer.entity.Customer;
import pl.training.bank.customer.repository.CustomerRepository;

import java.util.Optional;

@Transactional
@Log
//@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerService {

    @NonNull
    private CustomerRepository customerRepository;

    @NonNull
    private AccountService accountService;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public ResultPage<Customer> getCustomer(int pageNumber, int pageSize) {
        Page<Customer> result = customerRepository.findAll(PageRequest.of(pageNumber, pageSize));
        System.out.println(result);
        return new ResultPage<>(result.getContent(), pageNumber, result.getTotalPages());
    }

    public void assignAccount(long customerId, long accountId) {
        customerRepository.findById(customerId).ifPresent(customer -> {
            Account account = accountService.getAccountById(accountId);
            customer.getAccounts().add(account);
            customerRepository.save(customer);
        });
    }
}
