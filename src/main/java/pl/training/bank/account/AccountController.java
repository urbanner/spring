package pl.training.bank.account;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.common.UriBuilder;
import pl.training.bank.common.mapper.Mapper;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@RestController
public class AccountController {

    @NonNull
    private Mapper mapper;
    @NonNull
    private AccountService accountService;
    private UriBuilder uriBuilder = new UriBuilder();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAccount() {
        Account account = accountService.createAccount();
        URI uri = uriBuilder.requestUriWithId(account.getId());
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AccountDto getById(@PathVariable("id") Long id) {
        Account account = accountService.getById(id);
        return mapper.map(account, AccountDto.class);
    }


}
