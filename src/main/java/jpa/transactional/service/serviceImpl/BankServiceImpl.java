package jpa.transactional.service.serviceImpl;

import jpa.transactional.entity.Account;
import jpa.transactional.repository.AccountRepository;
import jpa.transactional.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionLogServiceImpl logService;

    @Transactional
    @Override
    public void transferWithRequired(Long fromId, Long toId, BigDecimal amount) {
        transferMoney(fromId, toId, amount);
        logService.logWithRequired("Transfer with REQUIRED");
    }

    @Transactional
    @Override
    public void transferWithRequiresNew(Long fromId, Long toId, BigDecimal amount) {
        transferMoney(fromId, toId, amount);
        try {
            logService.logWithRequiresNew("Transfer with REQUIRES_NEW");
        } catch (Exception e) {
            System.out.println("Logging failed (REQUIRES_NEW), transfer still committed.");
        }
    }

    @Transactional
    @Override
    public void transferWithNested(Long fromId, Long toId, BigDecimal amount) {
        transferMoney(fromId, toId, amount);
        try {
            logService.logWithNested("Transfer with NESTED");
        } catch (Exception e) {
            System.out.println("Logging failed (NESTED), main transaction not affected.");
        }
    }

    private void transferMoney(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountRepo.findById(fromId).orElseThrow();
        Account to = accountRepo.findById(toId).orElseThrow();

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepo.save(from);
        accountRepo.save(to);
    }
}
