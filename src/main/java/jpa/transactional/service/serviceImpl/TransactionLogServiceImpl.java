package jpa.transactional.service.serviceImpl;

import jpa.transactional.entity.TransactionLog;
import jpa.transactional.repository.TransactionLogRepository;
import jpa.transactional.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class TransactionLogServiceImpl implements TransactionService {

    @Autowired
    private TransactionLogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void logWithRequired(String message) {
        // nếu log lỗi sẽ rollback lại các phương thức chữa trong hàm ở trên log
        log(message);
        throw new RuntimeException("Simulated log failure - REQUIRED");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void logWithRequiresNew(String message) {
        // giả sử logWithRequiresNew được gọi trong một phương thức có transaction khác
        // khi logWithRequiresNew lỗi sẽ không ảnh hưởng đến phương thức khác
        log(message);
        throw new RuntimeException("Simulated log failure - REQUIRES_NEW");
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void logWithNested(String message) {
        // sẽ rollback lại nếu có lỗi
        log(message);
        throw new RuntimeException("Simulated log failure - NESTED");
    }

    // custom log
    private void log(String message) {
        TransactionLog log = new TransactionLog();
        log.setMessage(message);
        log.setTimestamp(Instant.now());
        logRepository.save(log);
    }
}
