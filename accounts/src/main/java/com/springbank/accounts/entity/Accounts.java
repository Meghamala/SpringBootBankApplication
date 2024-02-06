package com.springbank.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

// consider this class as entity representation for table with name Customer
@Entity
@Getter
@Setter
@ToString
// to create constructor
@AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity {

    // if column and field names are different, its useful
    @Column(name="customer_id")
    private Long customerID;

    // no annotations for account number generation as it will be done via another method
    @Id
    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;

}
