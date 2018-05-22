package pl.training.bank.operation.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntry, Long> {
}
