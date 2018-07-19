package pl.training.bank.disposition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Disposition {

    private String accountNumber;
    private long funds;
    private String operationName;

}
