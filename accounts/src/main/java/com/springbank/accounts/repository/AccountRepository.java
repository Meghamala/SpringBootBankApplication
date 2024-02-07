package com.springbank.accounts.repository;

import com.springbank.accounts.entity.Accounts;
import com.springbank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerID(Long customerID);
}
