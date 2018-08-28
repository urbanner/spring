package pl.training.bank.disposition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Disposition {

    private String accountNumber;
    private long funds;
    @Id
    private String operationName;

}
