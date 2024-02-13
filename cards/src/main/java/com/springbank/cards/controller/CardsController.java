package com.springbank.cards.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// REST APIs related to Cards Microservice
@RestController
public class CardsController {

    @GetMapping("sayHello")
    public String sayHello(){
        return "Hello World, Cards MS";
    }
}
