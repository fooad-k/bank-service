package com.bankservice.bankservice.entity;

import com.bankservice.bankservice.util.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 50)
    private Gender gender;

    @Column(name = "user_role", length = 20)
    private String userRole;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_by", length = 200)
    private String createdBy;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Account> account;

}
