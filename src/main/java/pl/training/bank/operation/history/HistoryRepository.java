package pl.training.bank.operation.history;

import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface HistoryRepository extends CrudRepository<HistoryEntry, Long> {

    @Query("select e from HistoryEntry e where e.funds >= :funds")
    List<HistoryEntry> getEntriesWithFunds(@Param("funds") long funds);

}
