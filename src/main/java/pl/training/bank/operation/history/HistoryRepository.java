package pl.training.bank.operation.history;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HistoryRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public void addEntry(HistoryEntry historyEntry) {
        entityManager.persist(historyEntry);
    }

}
