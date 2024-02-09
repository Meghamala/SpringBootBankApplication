package com.springbank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // contains all methods getter setter hash .. etc
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty") // to make this field mandatory and throw error msg if not validated
    @Size(min = 5, max = 30, message = "The length of name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
