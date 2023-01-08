package com.bankservice.bankservice.service;

public interface IAccountService {
    String login(String cardNumber, String pin, String fingerPrint, String authType);
    Boolean cardExist(String cardNumber);
    Boolean cashDispenser(String cardNumber, Double amount);
    Boolean checkDispenser(String cardNumber, Double amount);
    String withdraw(String cardNumber, Double amount);
    Boolean completeTransaction(String cardNumber, Long transactionId);
}
