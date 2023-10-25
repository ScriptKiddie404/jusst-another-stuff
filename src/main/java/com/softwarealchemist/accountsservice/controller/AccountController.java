package com.softwarealchemist.accountsservice.controller;

import static com.softwarealchemist.accountsservice.constants.AccountsConstants.*;

import com.softwarealchemist.accountsservice.dto.CustomerDTO;
import com.softwarealchemist.accountsservice.dto.ErrorResponseDTO;
import com.softwarealchemist.accountsservice.dto.ResponseDTO;
import com.softwarealchemist.accountsservice.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API for Accounts",
        description = "CRUD REST API to Create, Read, Update and Delete accounts details."
)
@RestController
@RequestMapping(path = "/api/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private final IAccountService accountService;

    private final Environment environment;

    public AccountController(IAccountService accountService, Environment environment) {
        this.accountService = accountService;
        this.environment = environment;
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API endpoint to create a new account."
    )
    @ApiResponse(
            responseCode = "201",
            description = "The account was created"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return new ResponseDTO(STATUS_201, MESSAGE_201);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "The mobile number must have 10 digits") String mobileNumber) {
        return accountService.fetchAccount(mobileNumber);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API endpoint to update an account."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account updated"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(STATUS_200, MESSAGE_200)).getBody();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(STATUS_500, MESSAGE_500)).getBody();
        }
    }

    @Operation(
            summary = "Delete account & customer details REST API",
            description = "REST API to delete customer and account details based on a mobile number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The account and customer were deleted from API."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "The mobile number must have 10 digits long") String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(STATUS_200, MESSAGE_200)).getBody();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(STATUS_500, MESSAGE_500)).getBody();
        }
    }

    @GetMapping("/environment")
    public ResponseEntity<String> getEnvironmentInfo(){
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }

}
