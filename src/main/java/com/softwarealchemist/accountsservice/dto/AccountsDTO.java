package com.softwarealchemist.accountsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Schema(
        name = "AccountsDTO",
        description = "Schema to hold account information."
)
@Data
public class AccountsDTO {

    @Schema(
            description = "The account number of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "Account number can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "The account number must have 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Software Alchemist account",
            example = "Savings"
    )
    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;

    @Schema(
            description = "Softwar Alchemist branch address"
    )
    @NotEmpty(message = "Branch address can't be null or empty")
    private String branchAddress;


}
