package pl.training.bank.operation.history;

import lombok.Data;
import pl.training.bank.account.Account;

import javax.persistence.*;
import java.util.Date;

@Table(name = "history")
@Entity
@Data
public class OperationHistoryEntry {

    @GeneratedValue
    @Id
    private Long id;
    @OneToOne
    private Account account;
    private long funds;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String type;

}
