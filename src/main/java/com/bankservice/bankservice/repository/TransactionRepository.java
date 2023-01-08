package com.bankservice.bankservice.repository;

import com.bankservice.bankservice.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Optional<Transaction> getByIdAndAccountId(Long id, Long accountId);

}
