package com.springbank.cards.service;

import com.springbank.cards.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param mobileNumber - Mobile Number of Customer
     */
    void createCards(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Card details based on mobile number
     */
    CardsDto fetchCards(String mobileNumber);
}
