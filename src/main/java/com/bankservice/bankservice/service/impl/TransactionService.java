package com.bankservice.bankservice.service.impl;

import com.bankservice.bankservice.entity.Transaction;
import com.bankservice.bankservice.repository.TransactionRepository;
import com.bankservice.bankservice.service.ITransactionService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;

    public Boolean create(Transaction transaction) {
        transactionRepository.save(transaction);
        return true;
    }

    public Boolean update(Transaction transaction) {
        transactionRepository.save(transaction);
        return true;
    }

    public Transaction getTransaction(Long id, Long accountId) {
        Optional<Transaction> byIdAndAccountId = transactionRepository.getByIdAndAccountId(id, accountId);
        return byIdAndAccountId.orElse(null);
    }
}
