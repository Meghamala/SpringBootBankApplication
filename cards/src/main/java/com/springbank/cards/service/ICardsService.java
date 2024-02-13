package com.springbank.cards.service;

import com.springbank.cards.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param mobileNumber - Mobile Number of Customer
     */
    void createCards(String mobileNumber);
}
