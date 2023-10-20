package com.softwarealchemist.accountsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDTO",
        description = "Schema to hold customer and account information"
)
public class CustomerDTO {

    @Schema(
            description = "The name of the customer",
            example = "Fernando Puentes"
    )
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 and 30 characters long")
    private String name;

    @Schema(
            description = "The email of the customer",
            example = "fernando@software.com"
    )
    @NotEmpty(message = "Name cant' be null or empty")
    @Email(message = "Email address should have valid format")
    private String email;

    @Schema(
            description = "The mobile number of the customer",
            example = "6143558888"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the given customer"
    )
    private AccountsDTO accountsDTO;

}
