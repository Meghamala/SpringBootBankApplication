package com.springbank.accounts.mapper;

import com.springbank.accounts.dto.CustomerDto;
import com.springbank.accounts.entity.Customer;

// for adding to H2 Db, we need actual customer info, that can be retrieved by converting CustomerDto to Customer
// and vice versa
public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
