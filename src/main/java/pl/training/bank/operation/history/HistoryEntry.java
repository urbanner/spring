package pl.training.bank.operation.history;

import lombok.Builder;
import lombok.Data;
import pl.training.bank.account.Account;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
public class HistoryEntry {

    @GeneratedValue
    @Id
    private Long id;
    @ManyToOne
    private Account account;
    private long funds;
    @Column(name = "operation_name")
    private String operationName;
    @Column(name = "operation_timestamp")
    private Date timestamp;

}
