package jpa.transactional.service;

public interface TransactionService {
    void logWithRequired(String message);

    void logWithRequiresNew(String message);

    void logWithNested(String message);
}
