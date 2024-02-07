package com.springbank.accounts.service;

import com.springbank.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);
}
