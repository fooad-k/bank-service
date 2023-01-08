package com.bankservice.bankservice.service.impl;

import com.bankservice.bankservice.entity.Account;
import com.bankservice.bankservice.entity.Failed;
import com.bankservice.bankservice.entity.Transaction;
import com.bankservice.bankservice.model.mapper.AccountMapper;
import com.bankservice.bankservice.repository.AccountRepository;
import com.bankservice.bankservice.service.IAccountService;
import com.bankservice.bankservice.service.IFailedService;
import com.bankservice.bankservice.service.ITransactionService;
import com.bankservice.bankservice.util.TransactionType;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final ITransactionService iTransactionService;
    private final IFailedService iFailedService;
    private final AccountMapper accountMapper;

    public Boolean cardExist(String cardNumber) {
        return accountRepository.getByCardNumber(cardNumber).isPresent();
    }


    public String login(String cardNumber, String pin, String fingerPrint, String authType) {
        Optional<Account> byCardNumber = accountRepository.getByCardNumber(cardNumber);
        if (byCardNumber.isPresent()) {
            if (byCardNumber.get().isBlock()) {
                return "isBlock";
            }
            if (Objects.equals(authType, "pin")) {
                if (Objects.equals(byCardNumber.get().getPinNumber(), pin)) {
                    setWrongPingCountToZero(byCardNumber.get());
                    return "ok";
                } else {
                    if (checkWrongPinThreshold(byCardNumber.get())) {
                        return "isBlock";
                    }
                    return "wrongPin";
                }
            } else {
                if (Objects.equals(byCardNumber.get().getFingerPrint(), fingerPrint)) {
                    setWrongPingCountToZero(byCardNumber.get());
                    return "ok";
                } else {
                    if (checkWrongPinThreshold(byCardNumber.get())) {
                        return "isBlock";
                    }
                    return "wrongFingerPrint";
                }
            }
        } else {
            return "notFind";
        }
    }

    private boolean checkWrongPinThreshold(Account account) {
        if (account.getWrongCount() == null)
            account.setWrongCount(0);
        account.setWrongCount((account.getWrongCount() + 1));

        boolean result = false;
        if (account.getWrongCount() >= 3) {
            account.setBlock(true);
            account.setBlockDate(new Date());
            result = true;
        }
        accountRepository.save(account);
        return result;
    }

    private void setWrongPingCountToZero(Account account) {
        account.setWrongCount(0);
        account.setBlockDate(null);
        accountRepository.save(account);
    }

    @Override
    public Boolean cashDispenser(String cardNumber, Double amount) {
        boolean state = false;
        Optional<Account> byCardNumber = accountRepository.getByCardNumber(cardNumber);
        if (byCardNumber.isPresent()) {
            try {
                byCardNumber.get().setBalance(byCardNumber.get().getBalance() + amount);
                accountRepository.save(byCardNumber.get());
                Transaction transaction = new Transaction();
                transaction.setAccount(byCardNumber.get());
                transaction.setTransaction(amount);
                transaction.setTransactionType(TransactionType.CASH_DISPOSER);
                transaction.setComplete(true);
                iTransactionService.create(transaction);
                state = true;

            } catch (RuntimeException runtimeException) {
                Failed failed = new Failed();
                failed.setAccount(byCardNumber.get());
                failed.setTransactionType(TransactionType.CASH_DISPOSER);
                failed.setReason("CashDisposer: " + runtimeException.getMessage());
                iFailedService.create(failed);
            }
        }
        return state;
    }

    @Override
    public Boolean checkDispenser(String cardNumber, Double amount) {
        boolean state = false;
        Optional<Account> byCardNumber = accountRepository.getByCardNumber(cardNumber);
        if (byCardNumber.isPresent()) {
            try {
                byCardNumber.get().setBalance(byCardNumber.get().getBalance() + amount);
                accountRepository.save(byCardNumber.get());
                Transaction transaction = new Transaction();
                transaction.setAccount(byCardNumber.get());
                transaction.setTransaction(amount);
                transaction.setTransactionType(TransactionType.CHECK_DISPOSER);
                transaction.setTransactionDate(new Date());
                transaction.setComplete(true);
                iTransactionService.create(transaction);
                state = true;

            } catch (RuntimeException runtimeException) {
                Failed failed = new Failed();
                failed.setAccount(byCardNumber.get());
                failed.setTransactionType(TransactionType.CHECK_DISPOSER);
                failed.setTransactionDate(new Date());
                failed.setReason("CheckDisposer: " + runtimeException.getMessage());
                iFailedService.create(failed);
            }
        }
        return state;
    }

    public String withdraw(String cardNumber, Double amount) {
        String state = "false";
        Optional<Account> byCardNumber = accountRepository.getByCardNumber(cardNumber);
        if (byCardNumber.isPresent()) {
            try {
                if (byCardNumber.get().getBalance() >= amount) {
                    byCardNumber.get().setBalance(byCardNumber.get().getBalance() - amount);
                    accountRepository.save(byCardNumber.get());
                    Transaction transaction = new Transaction();
                    transaction.setAccount(byCardNumber.get());
                    transaction.setTransaction(amount);
                    transaction.setTransactionType(TransactionType.WITHDRAW);
                    transaction.setTransactionDate(new Date());
                    transaction.setComplete(false);
                    iTransactionService.create(transaction);
                    state = transaction.getId().toString();
                } else {
                    state = "Your balance is not enough";
                }

            } catch (RuntimeException runtimeException) {
                Failed failed = new Failed();
                failed.setAccount(byCardNumber.get());
                failed.setTransactionType(TransactionType.WITHDRAW);
                failed.setTransactionDate(new Date());
                failed.setReason("CheckDisposer: " + runtimeException.getMessage());
                iFailedService.create(failed);
            }
        }
        return state;
    }

    public Boolean completeTransaction(String cardNumber, Long transactionId) {
        Optional<Account> byCardNumber = accountRepository.getByCardNumber(cardNumber);
        if (byCardNumber.isPresent()) {
            Transaction transaction = iTransactionService.getTransaction(transactionId, byCardNumber.get().getId());
            transaction.setComplete(true);
            iTransactionService.update(transaction);
            return true;
        }
        return false;
    }
}
