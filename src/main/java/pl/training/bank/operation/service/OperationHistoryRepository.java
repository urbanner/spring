package pl.training.bank.operation.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntry, Long> {
}
