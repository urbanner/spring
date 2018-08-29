package pl.training.bank.customer.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.account.dto.AccountDto;
import pl.training.bank.account.entity.Account;
import pl.training.bank.common.aop.ResultPage;
import pl.training.bank.common.dto.PageDto;
import pl.training.bank.common.mapper.Mapper;
import pl.training.bank.customer.entity.Customer;
import pl.training.bank.customer.entity.CustomerDto;
import pl.training.bank.customer.services.CustomerService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/customers", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
@RestController
public class CustomerController {

    @NonNull
    private CustomerService customerService;

    @NonNull
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getCustomers(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        System.out.println("AAAAAA");
        ResultPage<Customer> customerResultPage = customerService.getCustomer(pageNumber, pageSize);
        System.out.println("BBBBB");
        System.out.println(customerResultPage);
        return ResponseEntity.ok(customerResultPage);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity linkToAccount(@RequestBody CustomerDto customerDto){
        Customer customer = mapper.map(customerDto, Customer.class);
        customerService.createCustomer(customer);
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("{id}/accounts")
    public ResponseEntity assignAccountToCustomer(@PathVariable("id") long id, @RequestBody AccountDto accountDto){
        customerService.assignAccount(id, accountDto.getId());
        return ResponseEntity.notFound().build();
    }
}
