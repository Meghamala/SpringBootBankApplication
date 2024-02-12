package com.springbank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // contains all methods getter setter hash .. etc
@Schema( // to display table name instead of CustomerDto
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message="Account number must be 10 digits")
    @Schema(
            description = "Account Number of SpringBank account", example = "3454433243"
    )
    private Long accountNumber;

    @Schema(
            description = "Account type of SpringBank account", example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "SpringBank branch address", example = "123 NewYork"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
