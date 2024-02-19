package com.springbank.accounts.controller;

import com.springbank.accounts.constants.AccountConstants;
import com.springbank.accounts.dto.CustomerDto;
import com.springbank.accounts.dto.ErrorResponseDto;
import com.springbank.accounts.dto.ResponseDto;
import com.springbank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/* Rest APIs related to Accounts Microservice */

// To add a naming for API list
@Tag(
        name = "CRUD REST APIs for Accounts in SpringBank",
        description= "CRUD REST APIs in SpringBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
// means Im going to write methods with annotations related to http methods
// to expose all methods as Rest apis to world
@RestController
// all api will have path /api/... and response type is json
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated  // tells to perform vaidation on all REST APIs defined inside controller
public class AccountsController {

    @Autowired
    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    // to add description to each API on doc
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside SpringBank"
    )
    // to override default API response on doc
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
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

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content( // when 500 happens, application will follow schema defined in error dto
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )

            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
                                                               String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content( // when 500 happens, application will follow schema defined in error dto
                         schema= @Schema(implementation = ErrorResponseDto.class)
                    )

            )
    }
    )
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

    @Operation(
            summary = "Delete Account Details REST API",
            description = "REST API to delete Customer & Account details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content( // when 500 happens, application will follow schema defined in error dto
                            schema= @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
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
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get Build Information",
            description = "Get Build versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content( // when 500 happens, application will follow schema defined in error dto
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )

            )
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(
            summary = "Get Java Version Information",
            description = "Get Java versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content( // when 500 happens, application will follow schema defined in error dto
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )

            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                //.body(environment.getProperty("MAVEN_HOME"));
                .body(environment.getProperty("JAVA_HOME"));
    }
}
