package com.springbank.accounts.controller;

import com.springbank.accounts.constants.AccountConstants;
import com.springbank.accounts.dto.CustomerDto;
import com.springbank.accounts.dto.ResponseDto;
import com.springbank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Rest APIs related to Accounts Microservice

// means Im going to write methods with annotations related to http methods
// to expose all methods as Rest apis to world
@RestController
// all api will have path /api/... and response type is json
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    @Autowired
    private IAccountsService iAccountsService;

    // support http get method, to invoke API use this method with "sayHello" path
//    @GetMapping("sayHello")
//    public String sayHello(){
//        return "Hello World";
//    }

    // create method of return type of ResponseDto and it takes the values sent by customers in REquest body
    // and specifies Customer Dto to tell customer to send name, email, mobile as mentioned in this class
    @PostMapping("/create") // defines http post method, customer has to call /api/create and pass required values
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){

        iAccountsService.createAccount(customerDto);
        // responseentity is useful to send multiple segregated response to users like content, header.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }
}
