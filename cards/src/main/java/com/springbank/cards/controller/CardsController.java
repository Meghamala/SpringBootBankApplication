package com.springbank.cards.controller;

import com.springbank.cards.constants.CardsConstants;
import com.springbank.cards.dto.CardsDto;
import com.springbank.cards.dto.ResponseDto;
import com.springbank.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST APIs related to Cards Microservice
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CardsController {

//    @GetMapping("sayHello")
//    public String sayHello(){
//        return "Hello World, Cards MS";
//    }
    @Autowired
    private ICardsService iCardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCards(@RequestParam String mobileNumber){

        iCardsService.createCards(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCards(@RequestParam String mobileNumber){
        CardsDto cardsDto = iCardsService.fetchCards(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }
}
