package com.springbank.cards.service.impl;

import com.springbank.cards.constants.CardsConstants;
import com.springbank.cards.dto.CardsDto;
import com.springbank.cards.entity.Cards;
import com.springbank.cards.exception.CardAlreadyExistsException;
import com.springbank.cards.exception.ResourceNotFoundException;
import com.springbank.cards.mapper.CardsMapper;
import com.springbank.cards.repository.CardsRepository;
import com.springbank.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     *
     * @param mobileNumber - Mobile Number of Customer
     */
    @Override
    public void createCards(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }


        cardsRepository.save(CreateNewCard(mobileNumber));

    }


    private Cards CreateNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Card details based on mobile number
     */
    @Override
    public CardsDto fetchCards(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile", mobileNumber)
        );

        CardsDto cardsDto = CardsMapper.mapToCardsDto(cards, new CardsDto());
        return cardsDto;

    }

    /**
     * @param cardsDto - Input CardsDto object
     * @return - boolean indicating if the update of Card details is successful or not
     */
    @Override
    public Boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return - boolean indicating if the delete of Card details is successful or not
     */
    @Override
    public Boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardID());
        return true;
    }

}
