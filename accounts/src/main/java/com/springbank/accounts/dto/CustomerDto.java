package com.springbank.accounts.dto;

import lombok.Data;

@Data // contains all methods getter setter hash .. etc
public class CustomerDto {

    private String name;

    private String email;

    private String mobileNumber;
}
