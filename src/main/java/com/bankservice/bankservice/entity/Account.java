package com.bankservice.bankservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "card_number", length = 20)
    private String cardNumber;

    @Column(name = "pin_number", length = 4)
    private String pinNumber;

    @Column(name = "finger_print")
    private String fingerPrint;

    @Column(name = "wrong_count")
    private Integer wrongCount;

    @Column(name = "block")
    private boolean block;

    @Column(name = "block_date")
    private Date blockDate;

    @ManyToOne
    @JoinColumn(name = "person_Id")
    private Person person;

    @Column(name = "person_Id", insertable = false, updatable = false)
    private Long personId;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Failed> failed;





}
