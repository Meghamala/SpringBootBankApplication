package com.springbank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // contains all methods getter setter hash .. etc
@Schema( // to display table name instead of CustomerDto
        name = "Customer",
        description = "Schema to hold Customer, Account, Cards and Loans information"
)
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the customer", example = "SpringBank"
    )
    @NotEmpty(message = "Name cannot be null or empty") // to make this field mandatory and throw error msg if not validated
    @Size(min = 5, max = 30, message = "The length of name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "meg@springbank.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Cards details of the Customer"
    )
    private CardsDto cardsDto;

    @Schema(
            description = "Loans details of the Customer"
    )
    private LoansDto loansDto;
}
