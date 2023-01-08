package com.bankservice.bankservice.model;

import com.bankservice.bankservice.entity.Failed;
import com.bankservice.bankservice.entity.Person;
import com.bankservice.bankservice.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class AccountDTO {
    private Long id;
    private Double balance;
    private String cardNumber;
    private String pinNumber;
    private String fingerPrint;
    private boolean block;
    private Date blockDate;
    private Person person;
    private Long personId;
    private List<Transaction> transactions;
    private List<Failed> failed;

    @Getter
    @Setter
    public static class Info {
        private String cardNumber;
        private String pinNumber;
        private String fingerPrint;
        private boolean block;
    }
}
