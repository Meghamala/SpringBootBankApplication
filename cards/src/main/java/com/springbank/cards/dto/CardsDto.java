package com.springbank.cards.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CardsDto {

    private String mobileNumber;

    private String cardNumber;

    private String cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;


}
