package com.springbank.accounts.controller;

import com.springbank.accounts.constants.AccountConstants;
import com.springbank.accounts.dto.CustomerDto;
import com.springbank.accounts.dto.ResponseDto;
import com.springbank.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// Rest APIs related to Accounts Microservice

// means Im going to write methods with annotations related to http methods
// to expose all methods as Rest apis to world
@RestController
// all api will have path /api/... and response type is json
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated  // tells to perform vaidation on all REST APIs defined inside controller
public class AccountsController {

    @Autowired
    private IAccountsService iAccountsService;

    // create method of return type of ResponseDto and it takes the values sent by customers in REquest body
    // and specifies Customer Dto to tell customer to send name, email, mobile as mentioned in this class
    @PostMapping("/create") // defines http post method, customer has to call /api/create and pass required values
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        iAccountsService.createAccount(customerDto);
        // responseentity is useful to send multiple segregated response to users like content, header.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
                                                               String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateDetails(@Valid @RequestBody CustomerDto customerDto){
        Boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDetails(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
                                                         String mobileNumber){
        Boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
