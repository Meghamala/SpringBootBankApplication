package com.springbank.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest APIs related to Accounts Microservice

// means Im going to write methods with annotations related to http methods
// to expose all methods as Rest apis to world
@RestController
public class AccountsController {

    // support http get method, to invoke API use this method with "sayHello" path
    @GetMapping("sayHello")
    public String sayHello(){
        return "Hello World";
    }
}
