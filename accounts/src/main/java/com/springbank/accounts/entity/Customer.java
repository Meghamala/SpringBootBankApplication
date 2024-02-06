package com.springbank.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

// consider this class as entity representation for table with name Customer
@Entity
@Getter
@Setter
@ToString
// to create constructor
@AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {

    // to let spring JPA auto generate customer Ids
    // ID to say its primary key
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // if column and field names are different, its useful
    @Column(name="customer_id")
    private Long customerID;

    private String name;

    private String email;

    private String mobileNumber;

}
