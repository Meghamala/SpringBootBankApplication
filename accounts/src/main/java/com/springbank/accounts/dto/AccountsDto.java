package com.springbank.accounts.dto;

import lombok.Data;

@Data // contains all methods getter setter hash .. etc
public class AccountsDto {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
