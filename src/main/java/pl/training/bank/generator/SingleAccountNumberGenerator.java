package pl.training.bank.generator;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Generator(type = "single")
//@Service
@NoArgsConstructor
@RequiredArgsConstructor
public class SingleAccountNumberGenerator implements AccountNumberGenerator {

    @NonNull
    private String accountNumber;

    @Override
    public String getNext() {
        return accountNumber;
    }

}
