package com.softwarealchemist.accountsservice.controller;

import static com.softwarealchemist.accountsservice.constants.AccountsConstants.*;

import com.softwarealchemist.accountsservice.dto.CustomerDTO;
import com.softwarealchemist.accountsservice.dto.ResponseDTO;
import com.softwarealchemist.accountsservice.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

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

}
