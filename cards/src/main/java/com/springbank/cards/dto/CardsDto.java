package com.springbank.cards.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty")
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be null or empty")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private int availableAmount;


}
