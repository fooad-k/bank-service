package com.bankservice.bankservice.entity;

import com.bankservice.bankservice.util.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tbl_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_balance")
    private Double transaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_Id")
    private Account account;

    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;

    @Column(name = "complete")
    private boolean complete;

}
