package jpa.transactional.mapper;

import jpa.transactional.dto.TransactionLogDto;
import jpa.transactional.entity.TransactionLog;

public class TransactionMapper {
    public static TransactionLog mapTransactionLogDto(TransactionLogDto transactionLogDto) {
        return new TransactionLog(
                transactionLogDto.getId(),
                transactionLogDto.getMessage(),
                transactionLogDto.getTimestamp()
        );
    }

    public static TransactionLogDto mapTransactionLogDto(TransactionLog transactionLog) {
        return new TransactionLogDto(
                transactionLog.getId(),
                transactionLog.getMessage(),
                transactionLog.getTimestamp()
        );
    }
}
