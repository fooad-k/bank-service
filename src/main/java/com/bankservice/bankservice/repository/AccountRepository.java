package com.bankservice.bankservice.repository;

import com.bankservice.bankservice.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> getByCardNumber(String cardNumber);
}
