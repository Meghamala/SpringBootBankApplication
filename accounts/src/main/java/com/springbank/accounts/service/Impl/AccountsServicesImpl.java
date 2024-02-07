package com.springbank.accounts.service.Impl;

import com.springbank.accounts.constants.AccountConstants;
import com.springbank.accounts.dto.AccountsDto;
import com.springbank.accounts.dto.CustomerDto;
import com.springbank.accounts.entity.Accounts;
import com.springbank.accounts.entity.Customer;
import com.springbank.accounts.exception.CustomerAlreadyExistsException;
import com.springbank.accounts.exception.ResourceNotFoundException;
import com.springbank.accounts.mapper.AccountsMapper;
import com.springbank.accounts.mapper.CustomerMapper;
import com.springbank.accounts.repository.AccountRepository;
import com.springbank.accounts.repository.CustomerRepository;
import com.springbank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service // this is service layer which handles all creation work
@AllArgsConstructor // creates a constructor with given account and customer repo
public class AccountsServicesImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
    if(optionalCustomer.isPresent()){
        throw new CustomerAlreadyExistsException("Customer Already Registered with this mobileNumber"
                +customer.getMobileNumber());
    }
    customer.setCreatedBy("Megha");
    customer.setCreatedAt(LocalDateTime.now());
    Customer savedCustomer = customerRepository.save(customer); // customer is created in customer table and ID is returned
    accountRepository.save(createNewAccount(savedCustomer)); // new account is created using customer id

    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerID(customer.getCustomerID());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedBy("Megha");
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Account details based on mobile number
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile", mobileNumber)
        );

        Accounts account = accountRepository.findByCustomerID(customer.getCustomerID()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerID", customer.getCustomerID().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customerDto;
    }
}
