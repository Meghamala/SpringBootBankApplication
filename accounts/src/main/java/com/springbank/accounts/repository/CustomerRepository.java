package com.springbank.accounts.repository;

import com.springbank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// this is for connection between application and database
// jpa repo is coming from spring jpa framework giving all functionalities of db interaction
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByMobileNumber(String mobileNumer);
}
