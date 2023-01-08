package com.bankservice.bankservice.entity;

import com.bankservice.bankservice.util.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_failed")
public class Failed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_Id")
    private Account account;

    @Column(name = "account_id", insertable = false, updatable = false )
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "reason", length = 2000)
    private String reason;


}
