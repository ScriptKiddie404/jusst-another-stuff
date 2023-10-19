package com.softwarealchemist.accountsservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class AccountsDTO {

    @NotEmpty(message = "Account number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "The account number must have 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch address can't be null or empty")
    private String branchAddress;


}
