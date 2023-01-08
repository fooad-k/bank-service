package com.bankservice.bankservice.service;

import com.bankservice.bankservice.entity.Transaction;

public interface ITransactionService {
    Boolean create(Transaction transaction);
    Boolean update(Transaction transaction);
    Transaction getTransaction(Long id, Long accountId);
}
