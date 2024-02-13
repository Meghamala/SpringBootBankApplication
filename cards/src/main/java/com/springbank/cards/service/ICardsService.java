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

    /**
     *
     * @param cardsDto - Input CardsDto object
     * @return - boolean indicating if the update of Card details is successful or not
     */
    Boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return - boolean indicating if the delete of Card details is successful or not
     */
    Boolean deleteCard(String mobileNumber);
}
