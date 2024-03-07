package com.springbank.accounts.service.Impl;

import com.springbank.accounts.dto.*;
import com.springbank.accounts.entity.Accounts;
import com.springbank.accounts.entity.Customer;
import com.springbank.accounts.exception.ResourceNotFoundException;
import com.springbank.accounts.mapper.AccountsMapper;
import com.springbank.accounts.mapper.CustomerMapper;
import com.springbank.accounts.repository.AccountRepository;
import com.springbank.accounts.repository.CustomerRepository;
import com.springbank.accounts.service.ICustomerService;
import com.springbank.accounts.service.client.CardsFeignClient;
import com.springbank.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer details based on mobile number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile", mobileNumber)
        );

        Accounts account = accountRepository.findByCustomerID(customer.getCustomerID()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerID", customer.getCustomerID().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
