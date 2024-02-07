package com.springbank.accounts.mapper;

import com.springbank.accounts.dto.AccountsDto;
import com.springbank.accounts.entity.Accounts;

// for adding to H2 Db, we need actual Accounts info, that can be retrieved by converting AccountsDto to Account
// and vice versa
public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
