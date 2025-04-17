package jpa.transactional.service;

import java.math.BigDecimal;

public interface BankService {
    void transferWithRequired(Long fromId, Long toId, BigDecimal amount);

    void transferWithRequiresNew(Long fromId, Long toId, BigDecimal amount);

    void transferWithNested(Long fromId, Long toId, BigDecimal amount);
}
