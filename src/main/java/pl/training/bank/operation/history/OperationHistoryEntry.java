package pl.training.bank.operation.history;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "history")
@Entity
@Data
public class OperationHistoryEntry {

    @GeneratedValue
    @Id
    private Long id;
    private String accountNumber;
    private long funds;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String type;

}
