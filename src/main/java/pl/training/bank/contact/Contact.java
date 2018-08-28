package pl.training.bank.contact;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Contact {

    private Long id;
    @NonNull
    private String name;


}
