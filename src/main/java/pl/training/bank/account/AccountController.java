package pl.training.bank.account;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.common.ResultPage;
import pl.training.bank.common.UriBuilder;
import pl.training.bank.common.dto.PageDto;
import pl.training.bank.common.mapper.Mapper;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
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
        return ResponseEntity.created(uri).body(mapper.map(account, AccountDto.class));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AccountDto getById(@PathVariable("id") Long id) {
        Account account = accountService.getAccountById(id);
        return mapper.map(account, AccountDto.class);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAccounts(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        ResultPage<Account> accountsPage = accountService.getAccounts(pageNumber, pageSize);
        List<AccountDto> accountDtos = mapper.map(accountsPage.getData(), AccountDto.class);
        PageDto pageDto = new PageDto<>(accountDtos, accountsPage.getPageNumber(), accountsPage.getTotalPages());
        return ResponseEntity.ok(pageDto);
    }

    @RequestMapping(value = "{id}" , method = RequestMethod.DELETE)
    public ResponseEntity deleteAccountById(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
